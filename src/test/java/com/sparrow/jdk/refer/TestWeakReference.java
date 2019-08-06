package com.sparrow.jdk.refer;

import com.sparrow.jdk.volatilekey.User;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by harry on 2018/4/12.
 */
public class TestWeakReference {
   static WeakReference<User> user = new WeakReference<User>(new User(100, new byte[10000]));

    public static void main(String[] args) {
        int i = 0;
        while (true) {
            User u=user.get();
            if (user.get() != null) {
                i++;
                System.out.println("Object is alive for " + i + " loops - ");
            } else {
                System.out.println("Object has been collected.");
                break;
            }
            System.gc();
        }
    }
}
