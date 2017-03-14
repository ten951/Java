package com.wyt.headfirst.decorator;

/**
 * @author Darcy
 *         Created by Administrator on 2016/8/25.
 */
public abstract class Beverage {
    String description = "Unknown Beverage";

    public String getDescription() {
        return description;
    }
    public  abstract double cost();
}
