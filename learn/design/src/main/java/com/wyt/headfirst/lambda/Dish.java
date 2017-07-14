package com.wyt.headfirst.lambda;

/**
 * @author Darcy
 *         Created by Administrator on 2016/9/28.
 */
public class Dish {
    private final String name;
    private final boolean vegetarian;
    private final int calories;
    private final Type type;

    public Dish(boolean vegetarian, int calories, String name, Type type) {
        this.vegetarian = vegetarian;
        this.calories = calories;
        this.name = name;
        this.type = type;
    }

    public int getCalories() {
        return calories;
    }

    public String getName() {
        return name;
    }

    public Type getType() {
        return type;
    }

    public boolean isVegetarian() {
        return vegetarian;
    }

    public enum Type {
        MEAT, FISH, OTHER
    }
}
