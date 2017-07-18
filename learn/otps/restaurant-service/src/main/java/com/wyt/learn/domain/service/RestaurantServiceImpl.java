package com.wyt.learn.domain.service;

import com.wyt.learn.domain.model.entity.Entity;
import com.wyt.learn.domain.model.entity.Restaurant;
import com.wyt.learn.domain.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

/**
 * @author Darcy
 *         Created by Darcy on 2017/7/18.
 */
@Service("restaurantService")
public class RestaurantServiceImpl extends BaseService<Restaurant, String>
        implements RestaurantService {
    private RestaurantRepository<Restaurant, String> restaurantRepository;

    @Autowired
    public RestaurantServiceImpl(RestaurantRepository<Restaurant, String> restaurantRepository) {
        super(restaurantRepository);
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public void add(Restaurant restaurant) throws Exception {
        if (restaurantRepository.containsName(restaurant.getName())) {
            throw new Exception(String.format("There is already a product with the name - %s", restaurant.getName()));
        }

        if (restaurant.getName() == null || "".equals(restaurant.getName())) {
            throw new Exception("Restaurant name cannot be null or empty string.");
        }
        super.add(restaurant);
    }

    /**
     * @param name
     * @return
     * @throws Exception
     */
    @Override
    public Collection<Restaurant> findByName(String name) throws Exception {
        return restaurantRepository.findByName(name);
    }

    /**
     * @param restaurant
     * @throws Exception
     */
    @Override
    public void update(Restaurant restaurant) throws Exception {
        restaurantRepository.update(restaurant);
    }

    /**
     * @param id
     * @throws Exception
     */
    @Override
    public void delete(String id) throws Exception {
        restaurantRepository.remove(id);
    }

    /**
     * @param restaurantId
     * @return
     * @throws Exception
     */
    @Override
    public Entity findById(String restaurantId) throws Exception {
        return restaurantRepository.get(restaurantId);
    }

    /**
     * @param name
     * @return
     * @throws Exception
     */
    @Override
    public Collection<Restaurant> findByCriteria(Map<String, ArrayList<String>> name) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
