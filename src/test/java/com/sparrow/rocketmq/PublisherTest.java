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

package com.sparrow.rocketmq;

import com.sparrow.constant.cache.KEY;
import com.sparrow.constant.cache.key.KEY_FORUM;
import com.sparrow.container.Container;
import com.sparrow.container.impl.SparrowContainerImpl;
import com.sparrow.mq.MQPublisher;
import com.sparrow.rocketmq.protocol.event.HelloEvent;
import org.junit.Test;

/**
 * Created by harry on 2017/6/14.
 */
public class PublisherTest {
    public static void main(String[] args) {
        KEY key = new KEY.Builder().business(KEY_FORUM.ID_CODE_PAIR).businessId(1).build();
        Container container = new SparrowContainerImpl();
        container.init();
        MQPublisher mqPublisher = container.getBean("mqPublisher");
        HelloEvent helloEvent = new HelloEvent();
        helloEvent.setMessage("msg");
        try {
            while (true) {
                mqPublisher.publish(helloEvent, key);
            }
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }
}
