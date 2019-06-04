package com.sparrow.pipeline;

/**
 * @author by harry
 */
public class FourHander implements Handler<PipelineMain.PipelineData> {
    @Override public void invoke(PipelineMain.PipelineData arg) {
        arg.add(4);
        System.out.println(4);
    }
}
