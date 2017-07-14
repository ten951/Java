package com.wyt.headfirst.thread.producerconsumer;

import java.util.concurrent.BlockingDeque;

/**
 * @author Darcy
 *         Created by Administrator on 2017/5/8.
 */
public interface WorkStealingEnabledChannel<P> extends Channel<P> {
    P take(BlockingDeque<P> preferredQueue) throws InterruptedException;
}
