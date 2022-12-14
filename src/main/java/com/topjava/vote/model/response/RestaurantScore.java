package com.topjava.vote.model.response;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
public class RestaurantScore {
    private final long id;
    private final String name;
    private final long score;
}
