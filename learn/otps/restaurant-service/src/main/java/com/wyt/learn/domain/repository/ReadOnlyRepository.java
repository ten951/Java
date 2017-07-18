package com.wyt.learn.domain.repository;

import com.wyt.learn.domain.model.entity.Entity;

import java.util.Collection;

/**
 * 只读的存储库
 * @author Darcy
 *         Created by Darcy on 2017/7/18.
 */
public interface ReadOnlyRepository<TE, T> {
    boolean contains(T id);

    Entity get(T id);

    Collection<TE> getAll();
}
