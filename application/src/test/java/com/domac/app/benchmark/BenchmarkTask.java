package com.domac.app.benchmark;

/**
 * @author : lihaoquan
 */
public abstract class BenchmarkTask implements Runnable {


    /**
     * 线程执行方法
     */
    @Override
    public void run() {

    }

    abstract protected void execute(final int requestSequence);

    /**
     * Override for thread local connection and data setup.
     */
    protected void setUp() {
    }

    /**
     * Override for thread local connection and data cleanup.
     */
    protected void tearDown() {
    }
}
