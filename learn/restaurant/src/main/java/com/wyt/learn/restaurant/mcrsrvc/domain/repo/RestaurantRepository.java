package com.wyt.learn.restaurant.mcrsrvc.domain.repo;

/**
 * @author Darcy
 *         Created by Darcy on 2017/7/17.
 */
public interface RestaurantRepository<Restaurant, String> extends Repository<Restaurant, String> {
    boolean containsName(String name);
}
