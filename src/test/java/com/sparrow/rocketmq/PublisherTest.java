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
