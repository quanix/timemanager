package com.domac.app.common.benchmark;

import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author : lihaoquan
 *
 * 多线程Benchmark测试框架. 提供多线程调度及定时的测试进度, tps/latency信息打印.
 */
public abstract class ConcurrentBenchmark {

    public static final String THREAD_COUNT_NAME = "benchmark.thread.count";
    public static final String LOOP_COUNT_NAME = "benchmark.loop.count";

    public int threadCount;
    public long loopCount;

    public CountDownLatch startLock;
    public CountDownLatch finishLock;


    public Date startTime;
    public int intervalMillis = 10 * 1000;

    /**
     * 构造函数
     * @param defaultThreadCount
     * @param defaultLoopCount
     */
    public ConcurrentBenchmark(int defaultThreadCount,int defaultLoopCount) {
        this.threadCount = Integer.parseInt(System.getProperty(THREAD_COUNT_NAME, String.valueOf(defaultThreadCount)));
        this.loopCount = Long.parseLong(System.getProperty(LOOP_COUNT_NAME, String.valueOf(defaultLoopCount)));
        startLock = new CountDownLatch(threadCount);
        finishLock = new CountDownLatch(threadCount);
    }

    /**
     * 可被继承重写的初始化方法
     */
    protected void setUp() {
    }

    /**
     * 可被继承重写的注销方法
     */
    protected void tearDown() {
    }

    /**
     * 主要执行方法
     */
    public void execute() throws Exception {
        setUp();
        ExecutorService executorService = Executors.newFixedThreadPool(threadCount);
        try {

            for(int i = 0; i<threadCount; i++) {
                //Task执行
                BenchmarkTask benchmarkTask = createTask();
                benchmarkTask.parent = this;
                executorService.execute(benchmarkTask);
            }
            startLock.await();
            finishLock.await();

        }finally {
            executorService.shutdownNow();
            tearDown();
        }
    }


    protected abstract BenchmarkTask createTask();

    protected void setIntervalSeconds(int intervalSeconds) {
        this.intervalMillis = intervalSeconds * 1000;
    }

    protected void printStartMessage() {
        System.out.println("基准测试开始");
    }

    protected void printFinishMessage() {
        System.out.println("基准测试结束");
    }
}
