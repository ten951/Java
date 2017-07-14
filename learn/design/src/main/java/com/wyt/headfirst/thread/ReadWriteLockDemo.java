package com.wyt.headfirst.thread;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author Darcy
 *         Created by Administrator on 2016/12/8.
 */
public class ReadWriteLockDemo {
    private static Lock lock = new ReentrantLock();
    private static ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private static Lock readLock = readWriteLock.readLock();
    private static Lock writeLock = readWriteLock.writeLock();
    private int value;

    public Object handleRead(Lock lock) throws InterruptedException {
        try {
            lock.lock();
            Thread.sleep(1000);//模拟读操作
            return value;
        } finally {
            lock.unlock();
        }
    }

    public void handleWrite(Lock lock, int index) throws InterruptedException {
        try {
            lock.lock();
            Thread.sleep(1000);
            this.value = index;
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        final ReadWriteLockDemo demo = new ReadWriteLockDemo();
        Runnable readRunnable = () -> {
            try {
                //  Object o = demo.handleRead(readLock);//读写锁
                Object o1 = demo.handleRead(lock);//普通锁
                System.out.println("o = " + (int) o1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        Runnable writeRunnable = () -> {
            try {
                // demo.handleWrite(writeLock, new Random().nextInt());//读写锁
                demo.handleWrite(lock, new Random().nextInt());//普通锁
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        for (int i = 18; i < 20; i++) {
            new Thread(writeRunnable).start();
        }
        for (int i = 0; i < 18; i++) {
            new Thread(readRunnable).start();
        }
    }
}
