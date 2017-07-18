package com.wyt.learn.restaurant.mcrsrvc.domain.model;

/**
 * 抽象实体
 *
 * @author Darcy
 *         Created by Darcy on 2017/7/17.
 */
public abstract class Entity<T> {
    T id;
    String name;

    public T getId() {
        return id;
    }

    public void setId(T id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
