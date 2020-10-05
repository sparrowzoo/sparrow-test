package com.sparrow.limit;

import com.google.common.util.concurrent.RateLimiter;

public class LimitTest2 {
    public static void main(String[] args) throws InterruptedException {
        RateLimiter rateLimiter = RateLimiter.create(0.01);
        while (true) {
            System.out.println("get token "+ rateLimiter.acquire()+"s");
            Thread.sleep(5000L);
            System.out.println("get token "+ rateLimiter.acquire()+"s");
            System.out.println("get token "+ rateLimiter.acquire()+"s");
            System.out.println("get token "+ rateLimiter.acquire()+"s");
            System.out.println("get token "+ rateLimiter.acquire()+"s");
        }
    }
}
