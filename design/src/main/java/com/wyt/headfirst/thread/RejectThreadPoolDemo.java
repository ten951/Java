package com.wyt.headfirst.thread;

import java.util.concurrent.*;

/**
 * @author Darcy
 *         Created by Administrator on 2016/12/14.
 */
public class RejectThreadPoolDemo {
    public static class MyTask implements Runnable {
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
            System.out.println(System.currentTimeMillis() + ":Thread ID:" + Thread.currentThread().getId());
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public static void main(String[] args) throws InterruptedException {
            MyTask task = new MyTask();
            ExecutorService es = new ThreadPoolExecutor(5, 5,
                    0L, TimeUnit.MILLISECONDS,
                    new LinkedBlockingQueue<>(10),
                    Executors.defaultThreadFactory(),
                    (r, executor) -> System.out.println(r.toString() + " is discard"));
            for (int i = 0; i < Integer.MAX_VALUE; i++) {
                es.submit(task);
                Thread.sleep(10);
            }
        }
    }
}
