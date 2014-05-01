package com.domac.app.common.util;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author : lihaoquan
 *
 * 锁ID生成器
 */
public class LockIdBuilder {

    private static final Lock lock = new ReentrantLock();//定义一个ReentrantLock
    //上一次生成id的时间部分
    private static long lastTime = System.currentTimeMillis();
    //上一次生成id的计数器部分
    private static short lastCount = 0;
    //计数器最大值，当计数器当前值超过最大值时计数器清0
    private static int maxSize=10000;
    //新生成的id的时间部分
    private static long time;
    //新生成的id的计数器部分
    private static short count;


    public static long genId() {
        long newId;
        lock.lock();
        try {
            boolean done = false;
            while (!done) {
                long now = System.currentTimeMillis();
                if (now == lastTime) {
                    try {
                        Thread.currentThread().sleep(1);
                    } catch (java.lang.InterruptedException e) {
                    }
                    continue;
                } else {
                    lastTime = now;
                    done = true;
                }
            }
            if(lastCount==maxSize){
                lastCount=0;
            }
            time = lastTime;
            count = lastCount++;
            newId=time*maxSize+count;
        }finally {
            lock.unlock();
        }
        return newId;
    }
}
