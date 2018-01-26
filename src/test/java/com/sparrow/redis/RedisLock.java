package com.sparrow.redis;

import com.sparrow.concurrent.AbstractLock;

/**
 * Created by harry on 2018/1/26.
 */
public class RedisLock extends AbstractLock {
    @Override
    protected Boolean readLock(String key) {
        return false;
    }
}
