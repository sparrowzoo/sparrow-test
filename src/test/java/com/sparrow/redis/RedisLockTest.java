package com.sparrow.redis;

/**
 * Created by harry on 2018/1/26.
 */
public class RedisLockTest {
    public static void main(String[] args) {
        RedisLock redisLock=new RedisLock();
        redisLock.retryAcquireLock("s");
    }
}
