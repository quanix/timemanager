package com.domac.app.common.benchmark;

/**
 * @author : lihaoquan
 */
public abstract class BenchmarkTask implements Runnable {

    ConcurrentBenchmark parent;

    /**
     * 线程执行函数
     */
    @Override
    public void run() {
        try {
            for(int i = 0; i< parent.loopCount;i++) {
                execute(i);
            }
        }finally { //进行相关注销操作

        }
    }

    abstract protected void execute(final int requestSequence);
}
