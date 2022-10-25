package com.topjava.vote.argument;

import com.topjava.vote.model.dto.RestaurantDto;
import com.topjava.vote.model.entity.RestaurantEntity;

import java.util.HashSet;
import java.util.Set;

import static java.util.Collections.emptySet;

public class RestaurantTestArgument {
    
    public static RestaurantDto getDto() {
        return RestaurantDto.builder()
                            .id(1L)
                            .name("testRestaurant")
                            .dishes(emptySet())
                            .build();
        
    }
    
    public static RestaurantDto getDtoWithDishes() {
        return RestaurantDto.builder()
                            .id(1L)
                            .name("testRestaurant")
                            .dishes(new HashSet<>(Set.of(DishTestArgument.getDto())))
                            .build();
        
    }
    
    public static RestaurantEntity getEntity() {
        return RestaurantEntity.builder()
                               .id(1L)
                               .name("testRestaurant")
                               .dishes(emptySet())
                               .build();
        
    }
    
    public static RestaurantEntity getEntityWithDishes() {
        return RestaurantEntity.builder()
                               .id(1L)
                               .name("testRestaurant")
                               .dishes(new HashSet<>(Set.of(DishTestArgument.getEntity())))
                               .build();
        
    }
    
}
