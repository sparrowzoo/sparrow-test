package com.sparrow.jdk.threadlocal;

/**
 * @author by harry
 */
public class ThreadWithLocal extends Thread{
    public ThreadWithLocal(Long t) {
        this.t = t;
    }

    @Override public void run() {
        System.out.println(this.t);
    }

    //thread local
    private Long t;

    public static void main(String[] args) {
        ThreadWithLocal t=new ThreadWithLocal(System.nanoTime());
        t.start();
        ThreadWithLocal t2=new ThreadWithLocal(System.nanoTime());
        t2.start();
    }
}

