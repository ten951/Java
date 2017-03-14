package com.wyt.headfirst.decorator;

/**
 * 豆浆
 * @author Darcy
 *         Created by Administrator on 2016/8/25.
 */
public class Soy extends CondimentDecorator {
    Beverage beverage;

    public Soy(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + ",Soy";
    }

    @Override
    public double cost() {
        return .15 + beverage.cost();
    }
}
