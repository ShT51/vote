package com.topjava.vote.argument.provider;

import com.topjava.vote.argument.DishTestArgument;
import org.junit.jupiter.params.provider.Arguments;

import java.util.List;
import java.util.Set;

import static com.topjava.vote.argument.RestaurantTestArgument.getDto;
import static com.topjava.vote.argument.RestaurantTestArgument.getDtoWithDishes;
import static com.topjava.vote.argument.RestaurantTestArgument.getEntity;
import static com.topjava.vote.argument.RestaurantTestArgument.getEntityWithDishes;

public class RestaurantServiceArgumentsProvider {
    
    public static List<Arguments> getRestaurant() {
        return List.of(Arguments.of(
                getEntityWithDishes(),
                getDtoWithDishes()));
    }
    
    public static List<Arguments> getRestaurantEntityLazy() {
        return List.of(Arguments.of(
                        getEntity(),
                        getDto())
                      );
    }
    
    public static List<Arguments> getRestaurants() {
        return List.of(Arguments.of(
                        List.of(getEntityWithDishes()),
                        List.of(getDtoWithDishes()))
                      );
    }
    
    public static List<Arguments> saveRestaurant() {
        return getRestaurantEntityLazy();
    }
    
    public static List<Arguments> updateRestaurant() {
        var entity = getEntity();
        entity.setName("oldName");
        return List.of(Arguments.of(
                        entity,
                        getDto())
                      );
    }
    
    public static List<Arguments> addToMenu() {
        var dishDto1 = DishTestArgument.getDto();
        var dishDto2 = DishTestArgument.getDto();
        dishDto2.setId(2L);
        dishDto2.setName("newDish");
        var expectedDto = getDto();
        expectedDto.setDishes(Set.of(dishDto1, dishDto2));
        
        var dishEntity1 = DishTestArgument.getEntity();
        var dishEntity2 = DishTestArgument.getEntity();
        dishEntity2.setId(2L);
        dishEntity2.setName("newDish");
        
        return List.of(Arguments.of(
                        getEntityWithDishes(),
                        List.of(dishEntity1, dishEntity2),
                        expectedDto)
                      );
    }
    
    public static List<Arguments> removeFromMenu() {
        return List.of(Arguments.of(
                        getEntityWithDishes(),
                        List.of(DishTestArgument.getEntity()),
                        getDto())
                      );
    }
}