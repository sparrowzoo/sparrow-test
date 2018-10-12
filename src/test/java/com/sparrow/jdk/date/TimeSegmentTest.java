package com.sparrow.jdk.date;

import com.sparrow.constant.DATE_TIME;
import com.sparrow.core.Pair;
import com.sparrow.enums.DATE_TIME_UNIT;
import com.sparrow.utility.DateTimeUtility;
import org.apache.commons.lang3.time.DateUtils;

public class TimeSegmentTest {
    public static void main(String[] args) {
        Pair<Long, Long> pair = DateTimeUtility.getTimeSegment(DATE_TIME_UNIT.YEAR, System.currentTimeMillis());
        format(DATE_TIME_UNIT.YEAR,pair);
        pair = DateTimeUtility.getTimeSegment(DATE_TIME_UNIT.MONTH, System.currentTimeMillis());
        format(DATE_TIME_UNIT.MONTH,pair);
        pair = DateTimeUtility.getTimeSegment(DATE_TIME_UNIT.WEEK, System.currentTimeMillis());
        format(DATE_TIME_UNIT.WEEK,pair);
        pair = DateTimeUtility.getTimeSegment(DATE_TIME_UNIT.DAY, System.currentTimeMillis());
        format(DATE_TIME_UNIT.DAY,pair);
        pair = DateTimeUtility.getTimeSegment(DATE_TIME_UNIT.HOUR, System.currentTimeMillis());
        format(DATE_TIME_UNIT.HOUR,pair);
        pair = DateTimeUtility.getTimeSegment(DATE_TIME_UNIT.MINUTE, System.currentTimeMillis());
        format(DATE_TIME_UNIT.MINUTE,pair);
        pair = DateTimeUtility.getTimeSegment(DATE_TIME_UNIT.SECOND, System.currentTimeMillis());
        format(DATE_TIME_UNIT.SECOND, pair);
    }

    private static void format(DATE_TIME_UNIT unit,Pair<Long, Long> pair) {
        System.out.print(unit+"|"+DateTimeUtility.getFormatTime(pair.getFirst(), DATE_TIME.FORMAT_YYYY_MM_DD_HH_MM_SS_MS)+"|");

        System.out.println(DateTimeUtility.getFormatTime(pair.getSecond(), DATE_TIME.FORMAT_YYYY_MM_DD_HH_MM_SS_MS));
    }
}
