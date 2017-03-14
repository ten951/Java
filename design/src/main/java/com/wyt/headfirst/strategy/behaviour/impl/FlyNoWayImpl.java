package com.wyt.headfirst.strategy.behaviour.impl;

import com.wyt.headfirst.strategy.behaviour.IFlyBehavior;

/**
 * 没有翅膀的鸭子 实现飞行的方法
 *
 * @author Darcy
 *         Created by Administrator on 2016/8/19.
 */
public class FlyNoWayImpl implements IFlyBehavior {
    @Override
    public void fly() {
        System.out.println("什么都不做,我在地上跑!");
    }
}
