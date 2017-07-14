package com.wyt.headfirst.strategy.behaviour.impl;

import com.wyt.headfirst.strategy.behaviour.IQuackBehavior;

/**
 * 鸭子呱呱叫的实现类
 *
 * @author Darcy
 *         Created by Administrator on 2016/8/19.
 */
public class QuckImpl implements IQuackBehavior {
    @Override
    public void quack() {
        System.out.println("实现鸭子呱呱叫!");
    }
}
