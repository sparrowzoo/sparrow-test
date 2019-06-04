package com.sparrow.pipeline;

/**
 * @author by harry
 */
public class PipelineMain {
    static class PipelineData {
        private int data = 0;

        public void add(int n) {
            this.data += n;
        }

        public int getData() {
            return data;
        }

        public void setData(int data) {
            this.data = data;
        }
    }

    public static void main(String[] args) {
        HandlerPipeline handlerPipeline = new SimpleHandlerPipeline(true);
        handlerPipeline.add(new FirstHandler());
        handlerPipeline.add(new SecondHander());
        handlerPipeline.add(new ThreeHander());
        handlerPipeline.add(new FourHander());
        handlerPipeline.add(new FiveHander());
        PipelineData data = new PipelineData();
        handlerPipeline.fire(data);
        System.out.println(data.getData());
    }
}
