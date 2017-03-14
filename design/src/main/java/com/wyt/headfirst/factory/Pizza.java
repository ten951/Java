package com.wyt.headfirst.factory;

import java.util.ArrayList;

/**
 * 披萨的实体
 *
 * @author Darcy
 *         Created by Administrator on 2016/9/19.
 */
public abstract class Pizza {
    String name;
    String dough;
    String sauce;
    ArrayList toppings = new ArrayList();

    abstract void prepare();//在这个方法中,我们需要手机比萨所需要的原料,而这些原料当然是来自原料工厂了,

    public void bake() {
        System.out.println("Bake for 25 minutes at 350");
    }

    public void cut() {
        System.out.println("Cutting the pizza into diagonal slices");
    }

    public void box() {
        System.out.println("Place pizza in official PizzaStore box");
    }

    public String getName() {
        return name;
    }

}
