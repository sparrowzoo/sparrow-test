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

package com.sparrow;

import com.sparrow.orm.query.Criteria;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by harry on 16/12/8.
 */
public class DateTest {
    private static Logger logger= LoggerFactory.getLogger(DateTest.class);
    public static void main(String[] args) {
        Long t = System.currentTimeMillis();
        Date date = new Date(t);
        System.out.println(date);
        java.sql.Date d = new java.sql.Date(2015, 12, 11);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss EEEE");
        dateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        System.out.println(dateFormat.format(calendar.getTimeInMillis()));
        for (String f : TimeZone.getAvailableIDs()) {
           logger.info(f);
        }
    }

    static class T1 extends java.sql.Date {

        public T1(int year) {
            super(year);
        }
    }
}
