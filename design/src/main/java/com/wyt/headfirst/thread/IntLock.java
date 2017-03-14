package com.wyt.headfirst.thread;

import java.util.Vector;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Darcy
 *         Created by Administrator on 2016/12/7.
 */
public class IntLock implements Runnable {
    public static ReentrantLock lock1 = new ReentrantLock();
    public static ReentrantLock lock2 = new ReentrantLock();
    int lock;

    public IntLock(int lock) {
        this.lock = lock;
    }

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
        try {
            if (lock == 1) {
                lock1.lockInterruptibly();//锁1 这是一个可以对中断进行想要的锁申请动作!
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                lock2.lockInterruptibly();//锁2 加锁
            } else {
                lock2.lockInterruptibly();
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                lock1.lockInterruptibly();//锁1 加锁
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (lock1.isHeldByCurrentThread()) { //判断持有自己锁的线程是否是当前线程
                lock1.unlock();
            }
            if (lock2.isHeldByCurrentThread()) {
                lock2.unlock();
            }
            System.out.println(Thread.currentThread().getId() + ":线程退出");
        }
    }

    public String[] createStrings() {
        Vector<String> v = new Vector<>();
        for (int i = 0; i < 100; i++) {
            v.add(Integer.toString(i));
        }
        return v.toArray(new String[]{});
    }

    public static void main(String[] args) throws InterruptedException {
        IntLock r1 = new IntLock(1);//线程实例 1
        IntLock r2 = new IntLock(2);//线程实例 2
        Thread t1 = new Thread(r1);//线程1
        Thread t2 = new Thread(r2);//线程2
        t1.start();
        t2.start();
        Thread.sleep(5000);//Main线程 休眠1s

        t2.interrupt();//中断其中一个线程

    }
}
