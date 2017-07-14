package com.wyt.headfirst.thread.producerconsumer;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Semaphore;

/**
 * @author Darcy
 *         Created by Administrator on 2017/5/10.
 */
public class SemaphoreBasedChannel<P> implements Channel<P>  {
    private final BlockingQueue<P> queue;
    private final Semaphore semaphore;

    /**
     *
     * @param queue 阻塞队列，通常是一个无界阻塞队列。
     * @param flowLimit 流量限制数
     */
    public SemaphoreBasedChannel(BlockingQueue<P> queue, int flowLimit) {
        this.queue = queue;
        this.semaphore = new Semaphore(flowLimit);
    }

    @Override
    public P take() throws InterruptedException {
        return queue.take();
    }

    @Override
    public void put(P product) throws InterruptedException {
        semaphore.acquire();
        try {
            queue.put(product);
        } finally {
            semaphore.release();
        }
    }
}
