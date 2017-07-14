package com.wyt.headfirst.thread;

import java.text.MessageFormat;
import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Darcy
 *         Created by Administrator on 2016/12/21.
 */
public class ProducerConsumerModel {
    public static final class PCData {
        private final int intData;

        public int getIntData() {
            return intData;
        }

        public PCData(int intData) {
            this.intData = intData;
        }

        public PCData(String intData) {
            this.intData = Integer.valueOf(intData);
        }

        @Override
        public String toString() {
            return "data:" +
                    intData;
        }
    }


    /**
     * 生成者
     */
    public static class Producer implements Runnable {
        private volatile boolean isRunning = true;
        private BlockingQueue<PCData> queue;
        private AtomicInteger count = new AtomicInteger();
        private static final int SLEEPTIME = 1000;

        public Producer(BlockingQueue<PCData> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            PCData data = null;
            Random r = new Random();
            System.out.println("start producer id =" + Thread.currentThread().getId());
            try {
                while (isRunning) {
                    Thread.sleep(r.nextInt(SLEEPTIME));
                    data = new PCData(count.incrementAndGet());
                    System.out.println(data + " is put into queue");
                    if (!queue.offer(data, 2, TimeUnit.SECONDS)) {
                        System.out.println("failed to put data: " + data);
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        }

        public void stop() {
            isRunning = false;
        }
    }

    public static class Consumer implements Runnable {
        private BlockingQueue<PCData> queue;
        private static final int SLEEPTIME = 1000;

        public Consumer(BlockingQueue<PCData> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            System.out.println("start consumer id =" + Thread.currentThread().getId());
            Random r = new Random();
            try {
                while (true) {
                    PCData data = queue.take();
                    if (null != data) {
                        int re = data.getIntData() * data.getIntData();
                        System.out.println(MessageFormat.format("{0}*{1}={2}", data.getIntData(), data.getIntData(), re));
                        Thread.sleep(r.nextInt(SLEEPTIME));
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<PCData> queue = new LinkedBlockingDeque<>(10);
        Producer producer1 = new Producer(queue);
        Producer producer2 = new Producer(queue);
        Producer producer3 = new Producer(queue);
        Consumer consumer1 = new Consumer(queue);
        Consumer consumer2 = new Consumer(queue);
        Consumer consumer3 = new Consumer(queue);
        ExecutorService service = Executors.newCachedThreadPool();
        service.execute(producer1);
        service.execute(producer2);
        service.execute(producer3);
        service.execute(consumer1);
        service.execute(consumer2);
        service.execute(consumer3);
        Thread.sleep(10 * 1000);
        producer1.stop();
        producer2.stop();
        producer3.stop();
        Thread.sleep(3 * 1000);
        service.shutdown();

    }
}
