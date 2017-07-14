package com.wyt.headfirst.thread;

/**
 * @author Darcy
 *         Created by Administrator on 2016/12/6.
 */
public class CreateThread implements Runnable {


    @Override
    public void run() {
        while (true) {
            if (Thread.currentThread().isInterrupted()) {
                System.out.println("Interrupted");
                break;
            }
            try {
                Thread.sleep(2000);//实例线程 休眠2s  当线程休眠中被中断 抛出InterruptedException 异常
            } catch (InterruptedException e) {
                System.out.println("Interrupted When Sleep");
                Thread.currentThread().interrupt();
            }
            Thread.yield();
        }
    }


    public static void main(String[] args) throws InterruptedException {

        Thread t1 = new Thread(new CreateThread());
        t1.start();
        Thread.sleep(1000);//main 休眠1s 后执行中断
        t1.interrupt();
    }
}
