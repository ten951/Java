package com.wyt.learn.domain.model.entity;

/**
 * @author Darcy
 *         Created by Darcy on 2017/7/18.
 */
public class BaseEntity<T> extends Entity<T> {
    private boolean isModified;

    /**
     * @param id   唯一标识符
     * @param name 名称
     */
    public BaseEntity(T id, String name) {
        super.id = id;
        super.name = name;
        isModified = false;
    }

    /**
     * @return
     */
    public boolean isIsModified() {
        return isModified;
    }
}
