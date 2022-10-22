package com.topjava.vote.model.response;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class RestaurantScore {
    private final long id;
    private final String name;
    private final long score;
}
