package com.wyt.headfirst.thread.producerconsumer;

import java.util.concurrent.BlockingDeque;

/**
 * @author Darcy
 *         Created by Administrator on 2017/5/8.
 */
public class WorkStealingChannel<T> implements WorkStealingEnabledChannel<T> {

    //受管队列
    private final BlockingDeque<T>[] managedQueues;

    public WorkStealingChannel(BlockingDeque<T>[] managedQueues) {
        this.managedQueues = managedQueues;
    }

    @Override
    public T take(BlockingDeque<T> preferredQueue) throws InterruptedException {
        //优先从指定的受管队列中取"产品"
        BlockingDeque<T> targetQueue = preferredQueue;
        T product = null;
        //试图从指定的队列队首取"产品"
        if (null != targetQueue) {
            product = targetQueue.poll();
        }

        int queueIndex = -1;
        while (null == product) {
            queueIndex = (queueIndex + 1) % managedQueues.length;
            targetQueue = managedQueues[queueIndex];
            //试图从其他受管队列对尾"窃取""产品"
            product = targetQueue.pollLast();
            if (preferredQueue == targetQueue) {
                break;
            }

        }
        if (null == product) {
            //随机窃取其他手管队列的产品
            queueIndex = (int) (System.currentTimeMillis() % managedQueues.length);
            targetQueue = managedQueues[queueIndex];
            product = targetQueue.takeLast();
            System.out.println("stealed from " + queueIndex + ":" + product);
        }
        return product;
    }

    /**
     * 从通道中取出一个"产品"
     *
     * @return "产品"
     * @throws InterruptedException
     */
    @Override
    public T take() throws InterruptedException {
        return take(null);
    }

    /**
     * 往通道中存储一个"产品"
     *
     * @param product "产品"
     * @throws InterruptedException
     */
    @Override
    public void put(T product) throws InterruptedException {
        int targetIndex = product.hashCode() % managedQueues.length;
        BlockingDeque<T> targetQueue = managedQueues[targetIndex];
        targetQueue.put(product);
    }
}
