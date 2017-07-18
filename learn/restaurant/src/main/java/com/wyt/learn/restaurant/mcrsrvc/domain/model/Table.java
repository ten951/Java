package com.wyt.learn.restaurant.mcrsrvc.domain.model;

import java.math.BigInteger;

/**
 * 餐厅中桌子的实体
 *
 * @author Darcy
 *         Created by Darcy on 2017/7/17.
 */
public class Table extends BaseEntity<BigInteger> {

    //容量
    private int capacity;

    public Table(String name, BigInteger id, int capacity) {
        super(id, name);
        this.capacity = capacity;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

}
