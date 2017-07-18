package com.wyt.learn.domain.service;

import com.wyt.learn.domain.repository.Repository;

import java.util.Collection;

/**
 * @author Darcy
 *         Created by Darcy on 2017/7/18.
 */
public abstract class BaseService<TE,T> extends ReadOnlyBaseService<TE,T> {
    private Repository<TE, T> _repository;

    BaseService(Repository<TE, T> repository) {
        super(repository);
        _repository = repository;
    }

    /**
     *
     * @param entity
     * @throws Exception
     */
    public void add(TE entity) throws Exception {
        _repository.add(entity);
    }

    /**
     *
     * @return
     */
    public Collection<TE> getAll() {
        return _repository.getAll();
    }
}
