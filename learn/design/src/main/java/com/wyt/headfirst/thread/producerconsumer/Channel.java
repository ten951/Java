package com.wyt.headfirst.thread.producerconsumer;

/**
 * @author Darcy
 * Created by Administrator on 2017/5/8.
 */
public interface Channel<P> {
    /**
     * 从通道中取出一个"产品"
     *
     * @return "产品"
     * @throws InterruptedException
     */
    P take() throws InterruptedException;

    /**
     * 往通道中存储一个"产品"
     *
     * @param product "产品"
     * @throws InterruptedException
     */
    void put(P product) throws InterruptedException;
}


