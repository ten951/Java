package com.wyt.headfirst.strategy.behaviour.impl;

import com.wyt.headfirst.strategy.behaviour.IQuackBehavior;

/**
 * 橡皮鸭子吱吱叫的行为
 *
 * @author Darcy
 *         Created by Administrator on 2016/8/19.
 */
public class SqueakImpl implements IQuackBehavior {
    @Override
    public void quack() {
        System.out.println("橡皮鸭子吱吱叫!");
    }
}
