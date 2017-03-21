package com.wyt.headfirst.thinking.factory.factory.framework;

/**
 * 所有产品的父类,
 * 1.统一产品的类型(Product)
 * 2.定义产品的基本属性or方法(use)
 * 3.规定所有产品的流程.
 * @author Darcy
 *         Created by Administrator on 2017/3/21.
 */
public abstract class Product {
    public abstract void use();
}
