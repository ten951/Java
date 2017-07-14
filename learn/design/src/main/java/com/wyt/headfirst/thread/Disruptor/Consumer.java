package com.wyt.headfirst.thread.Disruptor;

import com.lmax.disruptor.WorkHandler;

/**
 * @author Darcy
 *         Created by Administrator on 2016/12/22.
 */
public class Consumer implements WorkHandler<PCData> {
    @Override
    public void onEvent(PCData event) throws Exception {
        System.out.println(Thread.currentThread().getId() + " :Event: --" + event.getValue() * event.getValue() + "--");
    }
}
