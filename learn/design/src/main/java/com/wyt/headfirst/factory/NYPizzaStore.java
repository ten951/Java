package com.wyt.headfirst.factory;

/**
 * @author Darcy
 *         Created by Administrator on 2016/9/19.
 */
public class NYPizzaStore extends PizzaStore {
    @Override
    Pizza createPizza(String type) {
        if (type.equals("cheese")) {
            return new NYStyleCheesePizza();
        } else {
            return null;
        }
    }
}
