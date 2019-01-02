package com.sparrow.jdk.threadlocal;

/**
 * @author by harry
 */
public class ThreadLocalGc {
    private static ThreadLocal<String> s=new ThreadLocal<>();

    public static void main(String[] args) {
        s.set("hello");
        System.out.println(s.get());
        System.gc();
        System.out.println(s.get());
    }
}
