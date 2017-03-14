package com.wyt.headfirst.decorator;

import java.io.FilterInputStream;

/**
 * @author Darcy
 *         Created by Administrator on 2016/8/25.
 */
public class StarbuzzCoffee {
    public static void main(String[] args) {
        Beverage beverage = new Espresso();//浓缩的咖啡
        System.out.println(beverage.getDescription() + " $" + beverage.cost());
        //深度烘烤 双倍摩卡 奶泡咖啡
        Beverage beverage2 = new DarkRoast();//深度烘烤
        beverage2 = new Mocha(beverage2);
        beverage2 = new Mocha(beverage2);
        beverage2 = new Whip(beverage2);
        System.out.println(beverage2.getDescription() + " $" + beverage2.cost());

        //豆浆 摩卡 奶泡的综合咖啡
        Beverage beverage3 = new HouseBlend();
        beverage3 = new Soy(beverage3);
        beverage3 = new Mocha(beverage3);
        beverage3 = new Whip(beverage3);
        System.out.println(beverage3.getDescription() + " $" + beverage3.cost());
    }
}
