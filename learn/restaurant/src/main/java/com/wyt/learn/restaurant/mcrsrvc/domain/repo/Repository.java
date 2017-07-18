package com.wyt.learn.restaurant.mcrsrvc.domain.repo;

/**
 * @author Darcy
 *         Created by Darcy on 2017/7/17.
 */
public interface Repository<TE, T> extends ReadOnlyRepository<TE, T> {
    void add(TE entity);

    void remove(T id);

    void update(TE entity);
}
