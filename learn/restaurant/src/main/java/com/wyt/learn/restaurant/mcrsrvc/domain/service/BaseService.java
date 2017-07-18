package com.wyt.learn.restaurant.mcrsrvc.domain.service;

import com.wyt.learn.restaurant.mcrsrvc.domain.repo.Repository;

import java.util.Collection;

/**
 * @author Darcy
 *         Created by Darcy on 2017/7/17.
 */
public class BaseService<TE, T> extends ReadOnlyBaseService<TE, T> {
    private Repository<TE, T> _repository;

    public BaseService(Repository<TE, T> repository) {
        super(repository);
        this._repository = repository;
    }

    public void add(TE entity) throws Exception {
        _repository.add(entity);
    }

    public Collection<TE> getAll() {
        return _repository.getAll();
    }
}
