package com.topjava.vote.service;

import com.topjava.vote.model.response.RestaurantScore;

import java.util.List;

public interface VoteService {
    
    void vote(long id, long restaurantId);
    
    List<RestaurantScore> getRestaurantsScore();
}
