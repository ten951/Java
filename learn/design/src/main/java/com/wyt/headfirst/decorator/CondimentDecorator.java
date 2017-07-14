package com.wyt.headfirst.decorator;

/**
 * 所有的装饰着需要集成的类
 * @author Darcy
 *         Created by Administrator on 2016/8/25.
 */
public abstract class CondimentDecorator extends Beverage {
    public abstract String getDescription();
}
