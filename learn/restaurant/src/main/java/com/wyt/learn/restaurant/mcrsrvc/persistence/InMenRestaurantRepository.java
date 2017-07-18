package com.wyt.learn.restaurant.mcrsrvc.persistence;

import com.wyt.learn.restaurant.mcrsrvc.domain.model.Entity;
import com.wyt.learn.restaurant.mcrsrvc.domain.model.Restaurant;
import com.wyt.learn.restaurant.mcrsrvc.domain.repo.RestaurantRepository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Darcy
 *         Created by Darcy on 2017/7/17.
 */
public class InMenRestaurantRepository implements RestaurantRepository<Restaurant, String> {
    private Map<String, Restaurant> entities;

    public InMenRestaurantRepository() {
        this.entities = new HashMap<>();
    }

    @Override
    public void add(Restaurant entity) {
        entities.put(entity.getName(), entity);
    }

    @Override
    public void remove(String id) {
        if (entities.containsKey(id)) {
            entities.remove(id);
        }

    }

    @Override
    public void update(Restaurant entity) {
        if (entities.containsKey(entity.getName())) {
            entities.put(entity.getName(), entity);
        }
    }

    @Override
    public boolean containsName(String name) {
        return entities.containsKey(name);
    }

    @Override
    public boolean contains(String id) {
        throw new UnsupportedOperationException("Not supported yes");
    }

    @Override
    public Entity get(String id) {
        throw new UnsupportedOperationException("Not supported yes");
    }

    @Override
    public Collection<Restaurant> getAll() {
        return entities.values();
    }
}
