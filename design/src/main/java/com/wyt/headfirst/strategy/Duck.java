package com.wyt.headfirst.strategy;

import com.wyt.headfirst.strategy.behaviour.IFlyBehavior;
import com.wyt.headfirst.strategy.behaviour.IQuackBehavior;

/**
 * 鸭子的实体的超类
 *
 * @author Darcy
 *         Created by Administrator on 2016/8/19.
 */
public abstract class Duck {
    private IFlyBehavior flyBehavior;
    private IQuackBehavior quackBehavior;


    public Duck() {
    }


    public IFlyBehavior getFlyBehavior() {
        return flyBehavior;
    }

    public void setFlyBehavior(IFlyBehavior flyBehavior) {
        this.flyBehavior = flyBehavior;
    }

    public IQuackBehavior getQuackBehavior() {
        return quackBehavior;
    }

    public void setQuackBehavior(IQuackBehavior quackBehavior) {
        this.quackBehavior = quackBehavior;
    }

    public abstract void display();

    public void performFly() {
        flyBehavior.fly();
    }

    public void performQuack() {
        quackBehavior.quack();
    }

    public void swim() {
        System.out.println("All ducks float, even decoys!");
    }
}
