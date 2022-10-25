package com.topjava.vote.argument;

import com.topjava.vote.model.entity.VoteEntity;

import java.time.LocalDate;

public class VoteTestArgument {
    
    public static VoteEntity getEntity() {
        return VoteEntity.builder()
                         .id(1L)
                         .voteDate(LocalDate.now())
                         .user(UserTestArgument.getEntity())
                         .restaurant(RestaurantTestArgument.getEntity())
                         .build();
        
    }
    
}
