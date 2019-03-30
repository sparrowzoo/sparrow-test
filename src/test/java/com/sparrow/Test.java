package cn.techwolf.trace.server.message;

import cn.techwolf.trace.server.constant.Constants;
import cn.techwolf.trace.server.dao.StatisticsBatchDao;
import cn.techwolf.trace.server.dao.es.model.QpsStatisticsModel;
import cn.techwolf.trace.server.dao.es.model.TpStatisticsModel;
import cn.techwolf.trace.server.service.parse.QpsTpStatisticsService;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: lichuan
 * @date: 2018/8/02 12:07
 * @description: 实时统计
 */
@Slf4j
@Service
@EnableAsync
public class SpanStatisticsListener {

    private static final String QPS_TABLE_NAME_FORMAT = "t_qps_statistics_%s";

    private static final String TP_TABLE_NAME_FORMAT = "t_tp_statistics_%s";

    private static final Map<Long, Boolean> CURRENT_CALCULATE_TS_MAP = Maps.newConcurrentMap();

    @Resource
    private StatisticsBatchDao statisticsBatchDao;

    @Resource
    @Qualifier("qpsTpStatisticsService")
    private QpsTpStatisticsService qpsTpStatisticsService;

    /**
     * 8个并发处理 多行span日志-->analysis rely
     *
     * @param records
     */
    @KafkaListener(topics = {"trace-model"}, groupId = "statisticsRealTime", containerFactory = "batchFactory")
    public void listenSlowHandel(List<ConsumerRecord<?, ?>> records) {
        List<String> infoMessages = buildInfoMessages(records);
        qpsTpStatisticsService.parseMessage(infoMessages);
    }

    private List<String> buildInfoMessages(List<ConsumerRecord<?, ?>> records) {
        List<String> allInfoMessage = Lists.newArrayListWithExpectedSize(records.size());
        for (ConsumerRecord<?, ?> record : records) {
            String recordStr = record.value().toString();
            allInfoMessage.addAll(Lists.newArrayList(Splitter.onPattern("\n").split(recordStr)));
        }
        return allInfoMessage;
    }

    @Async
    @Scheduled(cron = "0 0/1 * * * ?")
    public void clearQpsMap() {
        long timestamp15MinBefore = System.currentTimeMillis() / 60000 * 60000 - 15 * 60 * 1000L;

        Map<Long, Set<String>> serviceNameMap = qpsTpStatisticsService.getServiceNameMap();

        for (Map.Entry<Long, Set<String>> entry : serviceNameMap.entrySet()) {
            if (entry.getKey() <= timestamp15MinBefore) {
                serviceNameMap.remove(entry.getKey());
            }
        }

        ConcurrentMap<Long, ConcurrentMap<String, ConcurrentMap<String, AtomicInteger>>> qpsMap = qpsTpStatisticsService.getQpsMap();

        ConcurrentMap<Long, ConcurrentMap<String, ConcurrentMap<String, ConcurrentMap<Integer, AtomicInteger>>>> tpMap = qpsTpStatisticsService.getTpMap();

        for (Long ts : qpsMap.keySet()) {
            if (ts <= timestamp15MinBefore) {
                qpsMap.remove(ts);
            }
        }

        for (Long ts : tpMap.keySet()) {
            if (ts <= timestamp15MinBefore) {
                tpMap.remove(ts);
            }
        }
    }

    @Async
    @Scheduled(cron = "0 0/1 * * * ?")
    public void saveQpsTp99FromRedisToDB() {
        // 获取 5分钟前的时间戳
        long timestamp5MinBefore = System.currentTimeMillis() / 60000 * 60000 - 5 * 60 * 1000L;

        log.info("start to calculate qps and tp99 data " + DateFormatUtils.format(timestamp5MinBefore, "HH:mm"));

        // pair 存储 tsServiceName methodName
        ConcurrentMap<Long, ConcurrentMap<String, ConcurrentMap<String, AtomicInteger>>> qpsMap = qpsTpStatisticsService.getQpsMap();

        // triple 存储 tsServiceName methodName duration
        ConcurrentMap<Long, ConcurrentMap<String, ConcurrentMap<String, ConcurrentMap<Integer, AtomicInteger>>>> tpMap = qpsTpStatisticsService.getTpMap();

        // map 存储 ts serviceNameSet
        Map<Long, Set<String>> serviceNameMap = qpsTpStatisticsService.getServiceNameMap();

        List<Map.Entry<Long, Set<String>>> serviceNameList = Lists.newArrayList(serviceNameMap.entrySet());

        // 根据时间逆序
        serviceNameList.sort((i1, i2) -> i2.getKey().compareTo(i1.getKey()));

        for (Map.Entry<Long, Set<String>> serviceNameEntry : serviceNameList) {
            long statisticsTs = serviceNameEntry.getKey();

            String tsStr = DateFormatUtils.format(statisticsTs, "HH:mm");
            log.info("ts " + tsStr);
            // 只统计5分钟内的数据
            if (statisticsTs > timestamp5MinBefore) {
                continue;
            }

            try {
                Set<String> serviceNameSet = serviceNameMap.get(statisticsTs);
                if (CollectionUtils.isEmpty(serviceNameSet)) {
                    log.error("can't get service name " + tsStr);
                    continue;
                }

                if (CURRENT_CALCULATE_TS_MAP.putIfAbsent(statisticsTs, true) != null) {
                    log.info(statisticsTs + " under calculate");
                    continue;
                }

                log.info("ts " + tsStr + " service name: " + serviceNameSet);
                serviceNameSet.add(Constants.GLOBAL);

                List<QpsStatisticsModel> qpsList = Lists.newArrayListWithExpectedSize(2500);

                List<TpStatisticsModel> tpList = Lists.newArrayListWithExpectedSize(6400);

                for (String serviceName : serviceNameSet) {
                    ConcurrentMap<String, AtomicInteger> methodCountMap = qpsMap.getOrDefault(statisticsTs, new ConcurrentHashMap<>()).getOrDefault(serviceName, new ConcurrentHashMap<>());
                    for (String methodName : methodCountMap.keySet()) {
                        QpsStatisticsModel qpsStatisticsModel = new QpsStatisticsModel();
                        qpsStatisticsModel.setTimestamp(statisticsTs);
                        qpsStatisticsModel.setServiceName(serviceName);
                        qpsStatisticsModel.setApiName(methodName);
                        int callCount = methodCountMap.get(methodName).intValue();

                        methodCountMap.get(methodName).addAndGet(-callCount);
                        if (callCount <= 0) {
                            continue;
                        }
                        qpsStatisticsModel.setCallCount(Double.valueOf(callCount) / 60);
                        qpsList.add(qpsStatisticsModel);

                        // tp99 只计算-5分钟的数据
                        if (statisticsTs == timestamp5MinBefore) {
                            ConcurrentMap<Integer, AtomicInteger> durationCountMap = tpMap.getOrDefault(statisticsTs, new ConcurrentHashMap<>()).getOrDefault(serviceName, new ConcurrentHashMap<>()).get(methodName);
                            if (durationCountMap != null && !durationCountMap.isEmpty()) {
                                List<Map.Entry<Integer, AtomicInteger>> tpEntryList = Lists.newArrayList(durationCountMap.entrySet());
                                TpStatisticsModel tpStatisticsModel = new TpStatisticsModel();
                                tpStatisticsModel.setApiName(methodName);
                                tpStatisticsModel.setServiceName(serviceName);
                                tpStatisticsModel.setTimestamp(statisticsTs);

                                tpEntryList.sort(Comparator.comparing(o -> o.getKey()));

                                tpStatisticsModel.setMin(Double.valueOf(tpEntryList.get(0).getKey()));
                                tpStatisticsModel.setMax(Double.valueOf(tpEntryList.get(tpEntryList.size() - 1).getKey()));

                                setTpData(tpStatisticsModel, tpEntryList);
                                tpList.add(tpStatisticsModel);
                            }
                        }
                    }
                }
                if (qpsList.size() > 0) {
                    String tableTime = DateFormatUtils.format(statisticsTs,"yyyyMM");
                    String qpsTableName = String.format(QPS_TABLE_NAME_FORMAT, tableTime);
                    statisticsBatchDao.batchSaveQps2DB(qpsTableName, qpsList);
                    log.info("ts " + tsStr + " qps table name: " + qpsTableName + " qps list: " + qpsList.size());

                    if (tpList.size() > 0) {
                        String tpTableName = String.format(TP_TABLE_NAME_FORMAT, tableTime);
                        statisticsBatchDao.batchSaveTp2DB(tpTableName, tpList);
                    }

                    log.info("ts " + tsStr + " service name map size: " + serviceNameMap.size());
                    log.info("ts " + tsStr + " qps map size: " + qpsMap.size());
                    log.info("ts " + tsStr + " tp map size: " + tpMap.size());
                }
            } catch (Exception e) {
                log.error("exception", e);
            } finally {
                CURRENT_CALCULATE_TS_MAP.remove(statisticsTs);
            }
        }
    }

    private void setTpData(TpStatisticsModel tpStatisticsModel, List<Map.Entry<Integer, AtomicInteger>> entryList) {
        int totalCount = entryList.stream().mapToInt(e -> e.getValue().intValue()).sum();
        double tp95Count = totalCount * 0.95;
        double tp99Count = totalCount * 0.99;
        double tp999Count = totalCount * 0.999;
        double tp9999Count = totalCount * 0.9999;

        double tp95 = 0;
        double tp99 = 0;
        double tp999 = 0;
        double tp9999 = 0;

        boolean tp95Flag = false;
        boolean tp99Flag = false;
        boolean tp999Flag = false;
        boolean tp9999Flag = false;

        long count = 0;

        long totalDuration = 0;

        for (Map.Entry<Integer, AtomicInteger> entry : entryList) {
            totalDuration += (entry.getKey() * entry.getValue().intValue());

            count += entry.getValue().intValue();
            if (!tp95Flag && count >= tp95Count) {
                tp95 = entry.getKey();
                tp95Flag = true;
            }

            if (!tp99Flag && count >= tp99Count) {
                tp99 = entry.getKey();
                tp99Flag = true;
            }

            if (!tp999Flag && count >= tp999Count) {
                tp999 = entry.getKey();
                tp999Flag = true;
            }

            if (!tp9999Flag && count >= tp9999Count) {
                tp9999 = entry.getKey();
                tp9999Flag = true;
            }
        }

        double avg = ((double) totalDuration) / totalCount;
        tpStatisticsModel.setTp95(tp95);
        tpStatisticsModel.setTp99(tp99);
        tpStatisticsModel.setTp999(tp999);
        tpStatisticsModel.setTp9999(tp9999);
        tpStatisticsModel.setAvg(avg);
    }

}