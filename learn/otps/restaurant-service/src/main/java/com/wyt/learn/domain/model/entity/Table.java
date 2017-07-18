package com.wyt.learn.domain.model.entity;

import java.math.BigInteger;

/**
 * @author Darcy
 *         Created by Darcy on 2017/7/18.
 */
public class Table extends BaseEntity<BigInteger> {
    private int capacity;

    public Table(BigInteger id, String name, int capacity) {
        super(id, name);
        this.capacity = capacity;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    /**
     * Overridden toString() method that return String presentation of the
     * Object
     *
     * @return
     */
    @Override
    public String toString() {
        return "{id: " + id + ", name: " +
                name + ", capacity: " + capacity + "}";
    }
}
