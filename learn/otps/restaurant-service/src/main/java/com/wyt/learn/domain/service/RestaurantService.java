package com.wyt.learn.domain.service;

import com.wyt.learn.domain.model.entity.Entity;
import com.wyt.learn.domain.model.entity.Restaurant;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

/**
 * @author Darcy
 *         Created by Darcy on 2017/7/18.
 */
public interface RestaurantService {
    /**
     * @param restaurant
     * @throws Exception
     */
    void add(Restaurant restaurant) throws Exception;

    /**
     * @param restaurant
     * @throws Exception
     */
    void update(Restaurant restaurant) throws Exception;

    /**
     * @param id
     * @throws Exception
     */
    void delete(String id) throws Exception;

    /**
     * @param restaurantId
     * @return
     * @throws Exception
     */
    Entity findById(String restaurantId) throws Exception;

    /**
     * @param name
     * @return
     * @throws Exception
     */
    Collection<Restaurant> findByName(String name) throws Exception;

    /**
     * @param name
     * @return
     * @throws Exception
     */
    Collection<Restaurant> findByCriteria(Map<String, ArrayList<String>> name) throws Exception;
}
