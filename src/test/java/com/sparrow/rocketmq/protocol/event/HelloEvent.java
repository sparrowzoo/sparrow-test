package com.sparrow.rocketmq.protocol.event;

import com.sparrow.mq.MQEvent;

/**
 * Created by harry on 2017/6/14.
 */
public class HelloEvent implements MQEvent{
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
