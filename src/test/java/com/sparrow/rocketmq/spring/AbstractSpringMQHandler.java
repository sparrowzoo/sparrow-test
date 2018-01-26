package com.sparrow.rocketmq.spring;

import com.sparrow.mq.AbstractMQHandler;
import com.sparrow.mq.MQEvent;
import org.springframework.beans.factory.BeanNameAware;

/**
 * @author by harry
 */
public abstract class AbstractSpringMQHandler<T extends MQEvent> extends AbstractMQHandler<T> implements BeanNameAware {
    @Override public void setBeanName(String s) {
        this.aware(null, s);
    }
}
