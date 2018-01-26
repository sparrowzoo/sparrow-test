package com.sparrow.redis;

import com.sparrow.cache.CacheMonitor;
import com.sparrow.constant.cache.KEY;

/**
 * Created by harry on 2018/1/25.
 */
public class SparrowCacheMonitor implements CacheMonitor{
    @Override
    public boolean before(Long startTime, KEY key) {
        //System.out.println("start time"+startTime+" key"+key.key());
        return true;
    }

    @Override
    public void monitor(Long startTime, Long endTime, KEY key) {
        //System.out.println("module-"+key.getModule()+" business.type-"+key.getBusiness()+" key-"+key.key()+" start.time-"+startTime+" end.time-"+endTime);
    }
}
