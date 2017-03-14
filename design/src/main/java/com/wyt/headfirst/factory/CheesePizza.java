package com.wyt.headfirst.factory;

/**
 * @author Darcy
 *         Created by Administrator on 2016/9/19.
 */
public class CheesePizza extends Pizza {
    PizzaIngredientFactory ingredientFactory;

    /**
     * 要制作披萨 需要工厂提供原料.所以每个比萨类都需要从构造参数中得到一个工厂,并把这个工厂存储在一个实例变量中
     * @param ingredientFactory 原料工厂
     */
    public CheesePizza(PizzaIngredientFactory ingredientFactory) {
        this.ingredientFactory = ingredientFactory;
    }

    @Override
    void prepare() {

    }
}
