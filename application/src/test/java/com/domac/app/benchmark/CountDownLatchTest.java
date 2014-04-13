package com.domac.app.benchmark;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author : lihaoquan
 */
public class CountDownLatchTest {

    public static void main(String[] args) throws Exception {
        final CountDownLatch begin = new CountDownLatch(1);
        final CountDownLatch end = new CountDownLatch(10);

        /**
         * 建立10个运动员(往线程池容量为10)
         */
        final ExecutorService exec = Executors.newFixedThreadPool(10);

        for(int i = 0 ; i<10; i++) {
            final int _no = i+1;
            Runnable run = new Runnable() {
                @Override
                public void run() {
                    try {
                        begin.await();
                        Thread.sleep((long) (Math.random() * 10000));
                        System.out.println("No." + _no + " arrived");
                    }catch (InterruptedException e) {
                        e.printStackTrace();
                    }finally {
                        end.countDown();
                    }

                }
            };
            exec.execute(run);
        }

        System.out.println("比赛开始");
        begin.countDown();
        end.await();
        System.out.println("比赛结束");
        exec.shutdown();
    }
}
