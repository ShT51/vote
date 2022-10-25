package com.topjava.vote.argument;

import com.topjava.vote.model.response.RestaurantScore;

public class RestaurantScoreTestArgument {
    
    public static RestaurantScore getRestaurantScore() {
        return RestaurantScore.builder()
                              .id(1L)
                              .name("testRestaurant")
                              .score(100500L)
                              .build();
        
    }
    
}
