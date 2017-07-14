package com.wyt.headfirst.thread.producerconsumer;

import java.util.concurrent.BlockingQueue;

/**
 * @author Darcy
 *         Created by Administrator on 2017/5/8.
 */
public class BlockingQueueChannel<P> implements Channel<P> {
    private final BlockingQueue<P> queue;

    public BlockingQueueChannel(BlockingQueue<P> queue) {
        this.queue = queue;
    }

    /**
     * 从通道中取出一个"产品"
     *
     * @return "产品"
     * @throws InterruptedException
     */
    @Override
    public P take() throws InterruptedException {
        return queue.take();
    }

    /**
     * 往通道中存储一个"产品"
     *
     * @param product "产品"
     * @throws InterruptedException
     */
    @Override
    public void put(P product) throws InterruptedException {
        queue.put(product);
    }
}
