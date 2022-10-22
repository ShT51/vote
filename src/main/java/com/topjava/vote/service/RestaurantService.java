package com.topjava.vote.service;

import com.topjava.vote.model.dto.RestaurantDto;
import com.topjava.vote.model.entity.RestaurantEntity;

import java.util.List;
import java.util.Set;

public interface RestaurantService {
    RestaurantDto getRestaurant(long id);
    
    RestaurantEntity getRestaurantEntityLazy(long id);
    
    Set<RestaurantDto> getRestaurants();
    
    void saveRestaurant(RestaurantDto restaurantDto);
    
    RestaurantDto updateRestaurant(long id, RestaurantDto restaurantDto);
    
    void deleteRestaurant(long id);
    
    RestaurantDto addToMenu(long id, List<Long> dishIds);
    
    RestaurantDto removeFromMenu(long id, List<Long> dishIds);
    
}
