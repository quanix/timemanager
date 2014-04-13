package com.domac.app.benchmark;

import java.util.concurrent.CountDownLatch;

/**
 * @author : lihaoquan
 *
 * 参考Spring-Side Test 的 ConcurrentBenchmark
 *
 * 基准测试框架
 *
 */
public abstract class ConcurrentBenchmark {

    public static final String THREAD_COUNT_NAME = "benchmark.thread.count";
    public static final String LOOP_COUNT_NAME = "benchmark.loop.count";

    public int threadCount;
    public long loopCount;
    public int intervalMillis = 10 * 1000;//默认间隔为1秒

    /**
     * CountDownLatch，一个同步辅助类，在完成一组正在其他线程中执行的操作之前，它允许一个或多个线程一直等待。
     */
    public CountDownLatch startLock;
    public CountDownLatch finishLock;


    public ConcurrentBenchmark(int defaultThreadCount, long defaultLoopCount) {

    }

    public void excute() throws Exception {

    }

    protected void setIntervalSeconds(int intervalSeconds) {
        this.intervalMillis = intervalSeconds * 1000;
    }

    /**
     * Override for connection & data setup.
     */
    protected void setUp() {
    }

    /**
     * Override to connection & data cleanup.
     */
    protected void tearDown() {
    }
}
