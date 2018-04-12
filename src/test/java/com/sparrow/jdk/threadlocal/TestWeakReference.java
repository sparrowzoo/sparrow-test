package com.sparrow.jdk.threadlocal;

import com.sparrow.jdk.volatilekey.User;

import java.lang.ref.WeakReference;

/**
 * Created by harry on 2018/4/12.
 */
public class TestWeakReference {
    public static void main(String[] args) {

        //User user = ;
        WeakReference<User> weakCar = new WeakReference<User>(new User(22000));
        int i = 0;
        while (true) {
            System.gc();
            if (weakCar.get() != null) {
                i++;
                System.out.println("Object is alive for " + i + " loops - " + weakCar);
            } else {
                System.out.println("Object has been collected.");
                break;
            }
        }
    }
}
