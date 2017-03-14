package com.wyt.headfirst.decorator;

/**
 * 综合
 *
 * @author Darcy
 *         Created by Administrator on 2016/8/25.
 */
public class HouseBlend extends Beverage {
    public HouseBlend() {
        description = "House Blend coffee";
    }

    @Override
    public double cost() {
        return .89;
    }
}
