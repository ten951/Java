package com.wyt.headfirst.thinking.factory.factory.idcard;

import com.wyt.headfirst.thinking.factory.factory.framework.Factory;
import com.wyt.headfirst.thinking.factory.factory.framework.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 负责生产产品(IDCard)的工厂类, 继承工厂类.
 * 实现产品(IDCard)的创建,注册.
 *
 * @author Darcy
 *         Created by Administrator on 2017/3/21.
 */
public class IDCardFactory extends Factory {

    private int serial = 100;
    /*private List<String> owners = new ArrayList<>();*/

    private Map<Integer, String> datebase = new HashMap<>();

    @Override
    protected synchronized Product createProduct(String owner) {
        return new IDCard(owner, serial++);
    }

    @Override
    protected void registerProduct(Product product) {
        IDCard card = (IDCard) product;
        datebase.put(card.getSerial(), card.getOwner());
    }

    public Map<Integer, String> getDatebase() {
        return datebase;
    }

    /*public List<String> getOwners() {
        return owners;
    }*/
}
