package com.topjava.vote.argument.provider;

import com.topjava.vote.argument.RestaurantScoreTestArgument;
import com.topjava.vote.argument.RestaurantTestArgument;
import com.topjava.vote.argument.UserTestArgument;
import com.topjava.vote.argument.VoteTestArgument;
import org.junit.jupiter.params.provider.Arguments;

import java.util.List;

public class VoteServiceArgumentsProvider {
    
    public static List<Arguments> vote() {
        return List.of(Arguments.of(
                        UserTestArgument.getEntity(),
                        RestaurantTestArgument.getEntity(),
                        VoteTestArgument.getEntity())
                      );
    }
    
    public static List<Arguments> getRestaurantsScore() {
        return List.of(Arguments.of(List.of(RestaurantScoreTestArgument.getRestaurantScore())));
    }
}
