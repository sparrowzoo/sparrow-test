/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.sparrow.facade.date;

import com.sparrow.constant.DATE_TIME;
import com.sparrow.enums.DATE_TIME_UNIT;
import com.sparrow.utility.DateTimeUtility;
import org.junit.Test;

import java.util.Calendar;

/**
 * Created by harry on 2018/1/23.
 */
public class DateTimeUtilityTest {
    @Test
    public void floor() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis() + DATE_TIME.MILLISECOND_UNIT.get(DATE_TIME_UNIT.DAY) * 80);
        Long time = DateTimeUtility.floor(calendar, DATE_TIME_UNIT.SECOND);
        System.out.println("SECOND" + DateTimeUtility.getFormatTime(time, DATE_TIME.FORMAT_YYYYMMDDHHMMSS));
        time = DateTimeUtility.floor(calendar, DATE_TIME_UNIT.MINUTE);
        System.out.println("MINUTE" + DateTimeUtility.getFormatTime(time, DATE_TIME.FORMAT_YYYYMMDDHHMMSS));
        time = DateTimeUtility.floor(calendar, DATE_TIME_UNIT.HOUR);
        System.out.println("HOUR  " + DateTimeUtility.getFormatTime(time, DATE_TIME.FORMAT_YYYYMMDDHHMMSS));
        time = DateTimeUtility.floor(calendar, DATE_TIME_UNIT.DAY);
        System.out.println("DAY   " + DateTimeUtility.getFormatTime(time, DATE_TIME.FORMAT_YYYYMMDDHHMMSS));
        time = DateTimeUtility.floor(calendar, DATE_TIME_UNIT.WEEK);
        System.out.println("MONTH " + DateTimeUtility.getFormatTime(time, DATE_TIME.FORMAT_YYYYMMDDHHMMSS));
        time = DateTimeUtility.floor(calendar, DATE_TIME_UNIT.YEAR);
        System.out.println("YEAR  " + DateTimeUtility.getFormatTime(time, DATE_TIME.FORMAT_YYYYMMDDHHMMSS));
    }

    @Test
    public void ceiling() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis() + DATE_TIME.MILLISECOND_UNIT.get(DATE_TIME_UNIT.DAY) * 80);
        Long time = DateTimeUtility.ceiling(calendar, DATE_TIME_UNIT.SECOND);
        System.out.println("SECOND"+DateTimeUtility.getFormatTime(time, DATE_TIME.FORMAT_YYYYMMDDHHMMSS));
        time= DateTimeUtility.ceiling(calendar, DATE_TIME_UNIT.MINUTE);
        System.out.println("MINUTE"+DateTimeUtility.getFormatTime(time, DATE_TIME.FORMAT_YYYYMMDDHHMMSS));
        time= DateTimeUtility.ceiling(calendar, DATE_TIME_UNIT.HOUR);
        System.out.println("HOUR  "+DateTimeUtility.getFormatTime(time, DATE_TIME.FORMAT_YYYYMMDDHHMMSS));
        time= DateTimeUtility.ceiling(calendar, DATE_TIME_UNIT.DAY);
        System.out.println("DAY   "+DateTimeUtility.getFormatTime(time, DATE_TIME.FORMAT_YYYYMMDDHHMMSS));

        time= DateTimeUtility.ceiling(calendar, DATE_TIME_UNIT.MONTH);
        System.out.println("MONTH "+DateTimeUtility.getFormatTime(time, DATE_TIME.FORMAT_YYYYMMDDHHMMSS));
        time= DateTimeUtility.ceiling(calendar, DATE_TIME_UNIT.YEAR);
        System.out.println("YEAR  "+DateTimeUtility.getFormatTime(time, DATE_TIME.FORMAT_YYYYMMDDHHMMSS));
    }
    @Test
    public void interval() {
        Calendar calendar = Calendar.getInstance();
        Long time =calendar.getTimeInMillis();
        calendar.setTimeInMillis(time + DATE_TIME.MILLISECOND_UNIT.get(DATE_TIME_UNIT.DAY) * 2);
        System.out.println("Interval  " + DateTimeUtility.getInterval(time,calendar.getTimeInMillis(), DATE_TIME_UNIT.HOUR));
    }
}
