package com.domac.app.common.benchmark;

import com.domac.app.common.util.LockIdBuilder;

/**
 * @author : lihaoquan
 */
public class LockIdBanchmarkTest extends ConcurrentBenchmark{


    private static final int DEFAULT_THREAD_COUNT = 10;
    private static final int DEFAULT_LOOP_COUNT = 2;

    /**
     * 构造函数
     *
     */
    public LockIdBanchmarkTest() {
        super(DEFAULT_THREAD_COUNT, DEFAULT_LOOP_COUNT);
    }

    public static void main(String[] args) throws Exception {
        LockIdBanchmarkTest lockIdBanchmarkTest =  new LockIdBanchmarkTest();
        lockIdBanchmarkTest.execute();
     }

    @Override
    protected BenchmarkTask createTask() {
        return new LockIdTask();
    }

    public class LockIdTask extends BenchmarkTask{

        @Override
        protected void execute(int requestSequence) {
            System.out.println(LockIdBuilder.genId());
        }
    }

}
