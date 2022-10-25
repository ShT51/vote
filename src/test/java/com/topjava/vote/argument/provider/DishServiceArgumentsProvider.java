package com.topjava.vote.argument.provider;

import com.topjava.vote.argument.DishTestArgument;
import org.junit.jupiter.params.provider.Arguments;

import java.util.List;

public class DishServiceArgumentsProvider {
    
    public static List<Arguments> getDishByIdArgs() {
        return List.of(Arguments.of(
                DishTestArgument.getEntity(),
                DishTestArgument.getDto()));
    }
    
    public static List<Arguments> getDishes() {
        return List.of(Arguments.of(
                List.of(DishTestArgument.getEntity()),
                List.of(DishTestArgument.getDto())));
    }
    
    public static List<Arguments> getDishEntities() {
        return List.of(Arguments.of(List.of(DishTestArgument.getEntity())));
    }
    
    public static List<Arguments> saveDish() {
        return getDishByIdArgs();
    }
    
    public static List<Arguments> updateDish() {
        var oldEntity = DishTestArgument.getEntity();
        oldEntity.setName("oldDishName");
        oldEntity.setPrice(0.99);
        
        return List.of(Arguments.of(
                oldEntity,
                DishTestArgument.getDto()));
    }
    
}
