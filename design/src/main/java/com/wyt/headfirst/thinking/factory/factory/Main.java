package com.wyt.headfirst.thinking.factory.factory;

import com.wyt.headfirst.thinking.factory.factory.framework.Factory;
import com.wyt.headfirst.thinking.factory.factory.framework.Product;
import com.wyt.headfirst.thinking.factory.factory.idcard.IDCardFactory;

/**
 * 工厂方法模式:用模板方法模式构建生成实例的工厂
 * 工厂方法模式和模板方法模式的区别
 * 1,参与的角色不同,模板方法模式只有抽象类和具体类2个角色,
 *                工厂方法模式有产品,工厂,具体产品和具体工厂
 * 2,行为不同,工厂方法模式 产品-具体产品(模板方法模式) 工厂-具体工厂(模板方法模式)
 *   由于所有的产品都有统一的类型,所以 工厂就可以规定所有产品的流程.
 * @author Darcy
 *         Created by Administrator on 2017/3/21.
 */
public class Main {
    public static void main(String[] args) {
        //多态调用
        Factory factory = new IDCardFactory();
        /*
          背后流程
          1.Product card1 = new IDCard("小明");
          2.factory.registerProduct(card1)

         */
        Product card1 = factory.create("小明");
        Product card2 = factory.create("小红");
        Product card3 = factory.create("小冉");
        card1.use();
        card2.use();
        card3.use();
    }
}
