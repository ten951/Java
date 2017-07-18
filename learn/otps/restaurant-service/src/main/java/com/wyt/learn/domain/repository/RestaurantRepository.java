package com.wyt.learn.domain.repository;

import java.util.Collection;

/**
 * 餐厅存储库接口
 *
 * @author Darcy
 *         Created by Darcy on 2017/7/18.
 */
public interface RestaurantRepository<Restaurant, String> extends Repository<Restaurant, String> {
    /**
     * @param name
     * @return
     */
    boolean containsName(String name);

    /**
     * @param name
     * @return
     * @throws Exception
     */
    Collection<Restaurant> findByName(String name) throws Exception;
}
