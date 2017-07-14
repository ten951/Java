package com.wyt.headfirst.factory;

/**
 * @author Darcy
 *         Created by Administrator on 2016/9/19.
 */
public abstract class PizzaStore {
    public Pizza orderPizza(String type) {
        Pizza pizza;
        pizza = createPizza(type);
        pizza.prepare();
        pizza.bake();
        pizza.cut();
        pizza.box();
        return pizza;

    }

    abstract Pizza createPizza(String type);
}
