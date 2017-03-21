package com.wyt.headfirst.thinking.factory.factory.framework;

/**
 * 工厂类父类,规定了产品流程.
 * 1 创建产品
 * 2 注册产品
 * @author Darcy
 *         Created by Administrator on 2017/3/21.
 */
public abstract class Factory {
    public final Product create(String owner) {
        Product p = createProduct(owner);
        registerProduct(p);
        return p;
    }

    protected abstract Product createProduct(String owner);

    protected abstract void registerProduct(Product product);
}
