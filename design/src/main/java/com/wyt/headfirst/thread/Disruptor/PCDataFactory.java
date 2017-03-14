package com.wyt.headfirst.thread.Disruptor;

import com.lmax.disruptor.EventFactory;

/**
 * @author Darcy
 *         Created by Administrator on 2016/12/22.
 */
public class PCDataFactory implements EventFactory<PCData> {
    @Override
    public PCData newInstance() {
        return new PCData();
    }
}
