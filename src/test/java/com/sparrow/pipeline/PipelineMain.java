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

    public static void main(String[] args) throws InterruptedException {
        for(int i=0;i<1;i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    HandlerPipeline handlerPipeline = new SimpleHandlerPipeline(false);
                    handlerPipeline.add(new FirstHandler());
                    handlerPipeline.add(new SecondHander());
                    handlerPipeline.add(new ThreeHander());
                    handlerPipeline.add(new FourHander());
                    handlerPipeline.add(new FiveHander());
                    PipelineData data = new PipelineData();
                    handlerPipeline.fire(data);
                    //System.out.println(data.getData());
                }
            }).run();
        }
        Thread.sleep(10000);
    }
}
