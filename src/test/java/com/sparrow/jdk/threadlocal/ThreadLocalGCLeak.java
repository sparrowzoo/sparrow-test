package com.sparrow.jdk.threadlocal;

/**
 * Created by harry on 2018/4/12.
 */
public class ThreadLocalGCLeak extends Thread {
   static   ThreadLocal tl = new MyThreadLocal();
    public static class MyThreadLocal extends ThreadLocal {
        private byte[] a = new byte[1024 * 1024 * 1];
        @Override
        public void finalize() {
            System.out.println(" threadlocal 1 MB finalized.");
        }
    }

    public static class MyBigObject {//占用内存的大对象
        private byte[] a = new byte[1024 * 1024 * 50];
        @Override
        public void finalize() {
            System.out.println("50 MB Object finalized.");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                tl.set(new MyBigObject());
                MyBigObject o=(MyBigObject) tl.get();

                tl=null;//手动置为null，让gc回收
                System.gc();
                System.out.println(tl);
                System.out.println("Full GC 1");

                //保证线程不死，可以注释掉演示线程死亡的效果
                while (true){
                }
            }
        });
        thread.setDaemon(false);
        thread.start();
        System.out.println("Full GC 2");
        System.gc();
        Thread.sleep(1000);
        System.out.println("Full GC 3");
        System.gc();
        Thread.sleep(1000);
        System.out.println("Full GC 4");
        System.gc();
    }
}
