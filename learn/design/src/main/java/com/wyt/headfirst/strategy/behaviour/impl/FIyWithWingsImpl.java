package com.wyt.headfirst.strategy.behaviour.impl;

import com.wyt.headfirst.strategy.behaviour.IFlyBehavior;

/**
 * 有翅膀的鸭子飞行的实现类
 * @author Darcy
 *         Created by Administrator on 2016/8/19.
 */
public class FIyWithWingsImpl implements IFlyBehavior {
    @Override
    public void fly() {
        System.out.println("实现有翅膀鸭子的飞行行为: 我用翅膀飞行!");
    }
}
