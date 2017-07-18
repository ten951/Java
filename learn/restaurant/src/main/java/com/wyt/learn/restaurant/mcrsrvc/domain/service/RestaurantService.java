package com.wyt.learn.restaurant.mcrsrvc.domain.service;

import com.wyt.learn.restaurant.mcrsrvc.domain.model.Restaurant;
import com.wyt.learn.restaurant.mcrsrvc.domain.repo.Repository;
import com.wyt.learn.restaurant.mcrsrvc.domain.repo.RestaurantRepository;

import java.math.BigInteger;

/**
 * @author Darcy
 *         Created by Darcy on 2017/7/17.
 */
public class RestaurantService extends BaseService<Restaurant, String> {
    private RestaurantRepository<Restaurant, String> restaurantRepository;

    public RestaurantService(RestaurantRepository<Restaurant, String> repository) {
        super(repository);
        this.restaurantRepository = repository;
    }

    public void add(Restaurant restaurant) throws Exception {
        if (restaurantRepository.containsName(restaurant.getName())) {
            throw new Exception(String.format("There is already a product with the name - %s", restaurant.getName()));
        }

        if (restaurant.getName() == null || "".equals(restaurant.getName())) {
            throw new Exception("Restaurant name cannot be null or empty string.");
        }
        super.add(restaurant);
    }
}
