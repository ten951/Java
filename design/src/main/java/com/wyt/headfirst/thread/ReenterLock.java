package com.wyt.headfirst.thread;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Darcy
 *         Created by Administrator on 2016/12/7.
 */
public class ReenterLock implements Runnable {
    public static ReentrantLock lock = new ReentrantLock();
    public static int i = 0;


    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        for (int j = 0; j < 10000000; j++) {
            lock.lock();//加锁
            try {
                i++;
            } finally {
                lock.unlock();//释放锁
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ReenterLock t1 = new ReenterLock();//线程实例
        Thread th1 = new Thread(t1);
        Thread th2 = new Thread(t1);
        th1.start();
        th2.start();
        th1.join();
        th2.join();
        System.out.println("i = " + i);
    }
}
