package com.sparrow.jdk.date;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by harry on 16/7/28.
 */
public class TimeZoneTest {
    public static void main(String[] args) {
        /**
         * String var1 = System.getProperty("java.home") + File.separator + "lib";
         DataInputStream var2 = new DataInputStream(new BufferedInputStream(new FileInputStream(new File(var1, "tzdb.dat"))));
         */
        Calendar calendar = new GregorianCalendar();
//        System.out.print("时区：" + calendar.getTimeZone().getID() + "  ");
//        System.out.println("时间：" + calendar.get(Calendar.HOUR_OF_DAY) + ":" + calendar.get(Calendar.MINUTE));
        // 美国洛杉矶时区
        //TimeZone tz = TimeZone.getTimeZone("America/Los_Angeles");
        //calendar.setTimeZone(tz);
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.setTime(new Date("2017/10/15 00:00:01"));
        System.out.println(calendar.get(Calendar.DAY_OF_WEEK));

    }
}
