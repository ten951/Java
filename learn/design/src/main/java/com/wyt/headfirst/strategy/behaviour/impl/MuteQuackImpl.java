package com.wyt.headfirst.strategy.behaviour.impl;

import com.wyt.headfirst.strategy.behaviour.IQuackBehavior;

/**
 * 木头鸭子 不会叫
 *
 * @author Darcy
 *         Created by Administrator on 2016/8/19.
 */
public class MuteQuackImpl implements IQuackBehavior {
    @Override
    public void quack() {
        System.out.println("什么都不做,不会叫");
    }
}
