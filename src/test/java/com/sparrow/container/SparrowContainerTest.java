package com.sparrow.container;

import com.sparrow.container.impl.SparrowContainer;
import com.sparrow.support.Initializer;

/**
 * @author by harry
 */
public class SparrowContainerTest {
    public static void main(String[] args) throws Exception {
        Container container=new SparrowContainer();
        container.init();
        Initializer initializer= container.getBean("initializer");
        System.out.println(initializer);
    }
}
