package com.sparrow.rocketmq;

import com.sparrow.container.Container;
import com.sparrow.container.impl.SparrowContainerImpl;
import org.junit.Test;

/**
 * Created by harry on 2017/6/15.
 */
public class ConsumerTest {
    public void start() {
        Container container = new SparrowContainerImpl();
        container.init();
        container.getBean("mqConsumer");
    }
}
