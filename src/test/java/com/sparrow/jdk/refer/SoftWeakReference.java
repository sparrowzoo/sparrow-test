package com.sparrow.jdk.refer;

import com.sparrow.jdk.volatilekey.User;

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;

/**
 * -verbose:gc -Xloggc:d:\\jvm_gc_%t.log -Xmx60m -Xms60m -XX:+PrintTenuringDistribution -XX:+PrintGCDetails -XX:+PrintGCDateStamps  -XX:+UseParNewGC -XX:+UseConcMarkSweepGC -XX:CMSInitiatingOccupancyFraction=90 -XX:+UseCMSInitiatingOccupancyOnly
 * 60%3=20 年轻代20m 20/10=2 survivor=2M (2097152 2048k) eden=16M(16,777,216=16384K) cms=60-20=40M*0.1=4m
 * GC-Log-YG=Eden+Survivor=18M (18432K)
 */
public class SoftWeakReference {

    public static void main(String[] args) {
        SoftReference<List<User>> userListCache = new SoftReference<>(new ArrayList<User>());
        int i = 0;
        while (true) {
            User user = new User(100, new byte[1024 * 1024 * 1]);
            if (userListCache.get() != null) {
                i++;
                userListCache.get().add(user);
                System.out.println("YGC "+i);
            } else {
                System.out.println("end");
                System.exit(0);
            }
        }
    }
}
