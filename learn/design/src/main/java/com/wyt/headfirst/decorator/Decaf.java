package com.wyt.headfirst.decorator;

/**
 * 低咖啡因
 * @author Darcy
 *         Created by Administrator on 2016/8/25.
 */
public class Decaf extends Beverage {
    public Decaf() {
        description = "Decaf";
    }

    @Override
    public double cost() {
        return 1.05;
    }
}
