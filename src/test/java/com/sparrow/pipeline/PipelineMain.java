package com.sparrow.pipeline;

/**
 * @author by harry
 */
public class PipelineMain {
    public static void main(String[] args) {
        HandlerPipeline handlerPipeline=new SimpleHandlerPipeline();
        handlerPipeline.add(new FirstHandler());
        handlerPipeline.add(new SecondHander());
        handlerPipeline.add(new ThreeHander());
        handlerPipeline.add(new FourHander());
        handlerPipeline.add(new FiveHander());
        handlerPipeline.fire(1);
    }
}
