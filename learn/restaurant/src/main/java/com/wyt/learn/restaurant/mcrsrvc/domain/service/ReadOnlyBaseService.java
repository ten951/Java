package com.wyt.learn.restaurant.mcrsrvc.domain.service;

import com.wyt.learn.restaurant.mcrsrvc.domain.repo.Repository;

/**
 * 只读的服务
 *
 * @author Darcy
 *         Created by Darcy on 2017/7/17.
 */
public abstract class ReadOnlyBaseService<TE, T> {
    private Repository<TE, T> repository;

    public ReadOnlyBaseService(Repository<TE, T> repository) {
        this.repository = repository;
    }
}
