package com.wyt.headfirst.lambda;

/**
 * @author Darcy
 *         Created by Administrator on 2016/9/29.
 */
public class Trader {
    private final String name;
    private final String city;

    public Trader(String name, String city) {
        this.city = city;
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Trader{" +
                "city='" + city + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
