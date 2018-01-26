/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.sparrow.facade.latch;

import com.sparrow.cache.CacheClient;
import com.sparrow.cache.impl.redis.RedisCacheClient;
import com.sparrow.constant.SPARROW_MODULE;
import com.sparrow.constant.cache.KEY;
import com.sparrow.container.Container;
import com.sparrow.core.spi.ApplicationContext;
import com.sparrow.support.latch.DistributedCountDownLatch;
import com.sparrow.support.redis.impl.RedisDistributedCountDownLatch;

/**
 * Created by harry on 2018/1/11.
 */
public class RedisLatchTest {
    public static void main(String[] args) {
        Container container = ApplicationContext.getContainer();
        container.init();
        CacheClient cacheClient=new RedisCacheClient();

        KEY.Business code = new KEY.Business(SPARROW_MODULE.CODE, "ID", "NAME", "PAIR");
        KEY product=new KEY.Builder().business(code).businessId("1").build();
        final DistributedCountDownLatch distributedCountDownLatch = new RedisDistributedCountDownLatch(cacheClient,product);
        distributedCountDownLatch.product("1");
        distributedCountDownLatch.product("2");
        distributedCountDownLatch.consume("1");

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000 * 10);
                    distributedCountDownLatch.consume("2");
                } catch (InterruptedException e) {
                }
                System.out.println("consume 2");
            }
        }).start();
        distributedCountDownLatch.monitor(5);
        System.out.println("game over");
    }
}
