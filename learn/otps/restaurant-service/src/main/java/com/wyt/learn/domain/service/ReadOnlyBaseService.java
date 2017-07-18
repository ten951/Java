package com.wyt.learn.domain.service;

import com.wyt.learn.domain.repository.Repository;

/**
 * @author Darcy
 *         Created by Darcy on 2017/7/18.
 */
public abstract class ReadOnlyBaseService<TE, T> {
    private Repository<TE, T> repository;

    ReadOnlyBaseService(Repository<TE, T> repository) {
        this.repository = repository;
    }
}
