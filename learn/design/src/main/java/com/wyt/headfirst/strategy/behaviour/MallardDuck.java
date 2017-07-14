package com.wyt.headfirst.strategy.behaviour;

import com.wyt.headfirst.strategy.Duck;
import com.wyt.headfirst.strategy.behaviour.impl.FIyWithWingsImpl;
import com.wyt.headfirst.strategy.behaviour.impl.QuckImpl;

/**
 * @author Darcy
 *         Created by Administrator on 2016/8/19.
 */
public class MallardDuck extends Duck {
    @Override
    public void display() {
        System.out.println("I'm a real Mallard duck");
    }

    public MallardDuck() {
    }

    public static void main(String[] args) {
        Duck mallard = new MallardDuck();
        mallard.setFlyBehavior(new FIyWithWingsImpl());
        mallard.setQuackBehavior(new QuckImpl());
        mallard.performFly();
        mallard.performQuack();
    }
}
