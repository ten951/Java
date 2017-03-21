package com.wyt.headfirst.thinking.factory.factory.idcard;

import com.wyt.headfirst.thinking.factory.factory.framework.Product;

/**
 * 继承产品类,实现如何使用卡
 *
 * @author Darcy
 *         Created by Administrator on 2017/3/21.
 */
public class IDCard extends Product {
    private String owner;

    /**
     * 私有化构造器,目的是无法通过new来构造对象,只能通过工厂方法createProduct构建
     *
     * @param owner 拥有着
     */
    IDCard(String owner) {
        System.out.println(" 制作" + owner + " 的ID卡");
        this.owner = owner;
    }

    public String getOwner() {
        return owner;
    }

    @Override
    public void use() {
        System.out.println(" 使用" + owner + " 的ID卡");
    }
}
