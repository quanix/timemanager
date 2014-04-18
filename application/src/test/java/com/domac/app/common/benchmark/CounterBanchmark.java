package com.domac.app.common.benchmark;

/**
 * @author : lihaoquan
 */
public class CounterBanchmark extends ConcurrentBenchmark {

    private static final int DEFAULT_THREAD_COUNT = 10;
    private static final int DEFAULT_LOOP_COUNT = 2;


    /**
     * 构造函数
     */
    public CounterBanchmark() {
        super(DEFAULT_THREAD_COUNT, DEFAULT_LOOP_COUNT);
    }


    public static void main(String[] args) throws Exception {
        CounterBanchmark counterBanchmark = new CounterBanchmark();
        counterBanchmark.execute();
    }


    @Override
    protected void setUp() {
        System.out.println("CounterTask:setup");
    }

    @Override
    protected void tearDown() {
        System.out.println("CounterTask:tearDown");
    }

    @Override
    protected BenchmarkTask createTask() {
        return new CounterTask();
    }

    public class CounterTask extends BenchmarkTask {

        @Override
        protected void execute(int requestSequence) {
            System.out.println("CounterTask:execute:"+requestSequence);
        }
    }
}
