package com.wyt.headfirst.decorator;

/**
 * 浓缩
 * @author Darcy
 *         Created by Administrator on 2016/8/25.
 */
public class Espresso extends Beverage {

    public Espresso() {
        description = "Espresso";
    }

    @Override
    public double cost() {
        return 1.99;
    }
}
