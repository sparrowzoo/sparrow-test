package com.sparrow.pipeline;

/**
 * @author by harry
 */
public class FirstHandler implements Handler<PipelineMain.PipelineData> {

    @Override public void invoke(PipelineMain.PipelineData arg) {
        arg.add(1);
        System.out.println(1);
    }
}
