package com.wyt.headfirst.decorator;

/**
 * 深度烘培咖啡
 * @author Darcy
 *         Created by Administrator on 2016/8/25.
 */
public class DarkRoast extends  Beverage {

    public DarkRoast() {
        description = "Dark Roast";
    }

    @Override
    public double cost() {
        return .99;
    }
}
