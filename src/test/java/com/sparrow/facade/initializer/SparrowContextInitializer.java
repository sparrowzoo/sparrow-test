package com.sparrow.facade.initializer;

import com.sparrow.container.Container;
import com.sparrow.rocketmq.impl.RocketMQConsumer;
import com.sparrow.rocketmq.impl.SparrowRocketMQPublisher;
import com.sparrow.support.Initializer;
import org.apache.rocketmq.client.exception.MQClientException;

/**
 * Created by harry on 2018/1/19.
 */
public class SparrowContextInitializer implements Initializer {
    @Override
    public void init(Container container) {
        System.out.println("容器初始化...");
    }

    @Override
    public void destroy(Container container) {
        System.out.println("容器销毁...");
    }
}
