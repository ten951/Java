package com.wyt.headfirst.factory;

/**
 * @author Darcy
 *         Created by Administrator on 2016/9/19.
 */
public class NYStyleCheesePizza extends Pizza {
    public NYStyleCheesePizza() {
        name = "NY Style Sauce and Cheese Pizza";
        dough = "Thin Crust Dough";
        sauce = "Marinara Sauce";
        toppings.add("Grated Reggiano Cheese");

    }

    @Override
    void prepare() {

    }
}
