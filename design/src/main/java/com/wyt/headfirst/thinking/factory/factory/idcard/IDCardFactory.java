package com.wyt.headfirst.thinking.factory.factory.idcard;

import com.wyt.headfirst.thinking.factory.factory.framework.Factory;
import com.wyt.headfirst.thinking.factory.factory.framework.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * 负责生产产品(IDCard)的工厂类, 继承工厂类.
 * 实现产品(IDCard)的创建,注册.
 * @author Darcy
 *         Created by Administrator on 2017/3/21.
 */
public class IDCardFactory extends Factory {

    private List<String> owners = new ArrayList<>();

    @Override
    protected Product createProduct(String owner) {
        return new IDCard(owner);
    }

    @Override
    protected void registerProduct(Product product) {
        owners.add(((IDCard) product).getOwner());
    }

    public List<String> getOwners() {
        return owners;
    }
}
