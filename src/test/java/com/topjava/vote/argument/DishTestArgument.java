package com.topjava.vote.argument;

import com.topjava.vote.model.dto.DishDto;
import com.topjava.vote.model.entity.DishEntity;

import java.util.HashSet;

public class DishTestArgument {
    
    public static DishDto getDto() {
        return DishDto.builder()
                      .id(1L)
                      .name("testDish")
                      .price(100500.9)
                      .available(true)
                      .build();
        
    }
    
    public static DishEntity getEntity() {
        return DishEntity.builder()
                         .id(1L)
                         .name("testDish")
                         .price(100500.9)
                         .available(true)
                         .restaurants(new HashSet<>())
                         .build();
        
    }
}
