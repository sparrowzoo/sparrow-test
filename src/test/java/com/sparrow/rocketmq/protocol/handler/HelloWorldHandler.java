package com.sparrow.rocketmq.protocol.handler;

import com.sparrow.mq.AbstractMQHandler;
import com.sparrow.rocketmq.protocol.event.HelloEvent;
import com.sparrow.rocketmq.spring.AbstractSpringMQHandler;
import org.springframework.beans.factory.BeanNameAware;

/**
 * Created by harry on 2017/6/14.
 */
public class HelloWorldHandler extends AbstractSpringMQHandler<HelloEvent>{
    public HelloWorldHandler() {
        System.out.println("init a hello world handler");
    }

    public void handle(HelloEvent event) throws Throwable {
        System.out.println(event.getMessage());
    }

    @Override
    public void setBeanName(String s) {
        this.aware(null, s);
    }
}
