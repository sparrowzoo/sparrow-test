package com.sparrow.jdk.threadlocal;

import com.sparrow.jdk.volatilekey.User;

/**
 * Created by harry on 2018/4/12.
 */
public class ThreadLocalTest extends Thread {
    public static class MyThreadLocal extends ThreadLocal {
        private byte[] a = new byte[1024 * 1024 * 1];

        @Override
        public void finalize() {
            System.out.println("My threadlocal 1 MB finalized.");
        }
    }

    public static class My50MB {//占用内存的大对象
        private byte[] a = new byte[1024 * 1024 * 50];

        @Override
        public void finalize() {
            System.out.println("My 50 MB finalized.");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                ThreadLocal tl = new MyThreadLocal();
                tl.set(new My50MB());

                tl=null;//断开ThreadLocal的强引用
                System.out.println("Full GC 1");
                System.gc();

                while (true) {
                }
            }
        });
        thread.start();
        System.out.println("Full GC 2");
        System.gc();
        Thread.sleep(1000);
        System.out.println("Full GC 3");
        System.gc();
        Thread.sleep(1000);
        System.out.println("Full GC 4");
        System.gc();
        while (true) {
        }
    }
}
