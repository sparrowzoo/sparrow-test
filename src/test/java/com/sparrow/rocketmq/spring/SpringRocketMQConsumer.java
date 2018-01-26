package com.sparrow.rocketmq.spring;

import com.sparrow.rocketmq.impl.RocketMQConsumer;
import org.springframework.beans.factory.InitializingBean;

/**
 * Created by harry on 2018/1/19.
 */
public class SpringRocketMQConsumer extends RocketMQConsumer implements InitializingBean {
    @Override
    public void afterPropertiesSet() throws Exception {
        this.start();
    }
}
