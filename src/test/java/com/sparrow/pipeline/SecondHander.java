package com.sparrow.pipeline;

/**
 * @author by harry
 */
public class SecondHander implements Handler<PipelineMain.PipelineData> {
    @Override public void invoke(PipelineMain.PipelineData arg) {
        arg.add(2);
        System.out.println("tid="+Thread.currentThread().getId()+"- 2");
    }
}
