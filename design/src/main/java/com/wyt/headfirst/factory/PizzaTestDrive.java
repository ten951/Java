package com.wyt.headfirst.factory;

/**
 * @author Darcy
 *         Created by Administrator on 2016/9/19.
 */
public class PizzaTestDrive {
    public static void main(String[] args) {
        PizzaStore nyStore = new NYPizzaStore();

        Pizza pizza = nyStore.orderPizza("cheese");
        System.out.println("name " + pizza.getName());

    }

}
