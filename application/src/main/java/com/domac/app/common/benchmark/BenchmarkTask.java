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
        setUp();
        onThreadStart();
        try {
            for(int i = 0; i< parent.loopCount;i++) {
                parent.latch.await();
                execute(i);
            }
        }catch (Exception e) {

        }finally { //进行相关注销操作
            tearDown();
            onThreadFinish();
        }
    }

    abstract protected void execute(final int requestSequence);

    protected void onThreadStart() {
        parent.startLock.countDown();
        try {
            parent.startLock.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    protected void onThreadFinish() {
        parent.finishLock.countDown();
    }

    protected void setUp() {
    }

    protected void tearDown() {
    }

    /**
     * 间隔固定时间打印进度信息.
     */
    protected void printProgressMessage(final int currentRequests) {
        System.out.println("执行进度:"+currentRequests);
    }
}
