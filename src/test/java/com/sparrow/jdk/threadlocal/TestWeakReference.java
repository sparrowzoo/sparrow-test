package com.sparrow.jdk.threadlocal;

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
    SoftReference<Set<User>> users = new SoftReference<Set<User>>(new HashSet<User>());

    public static void main(String[] args) {
        //User user = ;
        TestWeakReference testWeakReference = new TestWeakReference();
        int i = 0;
        while (true) {
            if (testWeakReference.users.get() != null) {
                testWeakReference.users.get().add(new User(100, new byte[10000]));
                i++;
                System.out.println("Object is alive for " + i + " loops - ");
            } else {
                System.out.println("Object has been collected.");
                break;
            }
            System.gc();
            Runtime.getRuntime().runFinalization();
        }
    }
}
