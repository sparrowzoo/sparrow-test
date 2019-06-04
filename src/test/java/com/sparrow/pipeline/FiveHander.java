package com.sparrow.pipeline;

/**
 * @author by harry
 */
public class FiveHander implements Handler {
    @Override public void invoke(Object arg) {
        System.out.println(5);
    }
}
