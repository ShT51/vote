package com.topjava.vote.controller;

import com.topjava.vote.model.AuthenticatedUser;
import com.topjava.vote.model.response.ResponseData;
import com.topjava.vote.model.response.RestaurantScore;
import com.topjava.vote.service.VoteService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.topjava.vote.cache.VoteCacheManager.VOTE_CACHE;

@Slf4j
@RestController
@RequiredArgsConstructor
public class VoteController {
    
    private final VoteService voteService;
    
    @Operation(summary = "Vote for the favorite Restaurant for today", tags = "vote")
    @PostMapping("/vote")
    @CacheEvict(cacheNames = VOTE_CACHE, allEntries = true)
    public ResponseData<Object> voteForRestaurant(@AuthenticationPrincipal AuthenticatedUser user,
                                                  @RequestParam(name = "restaurantId") long id) {
        voteService.vote(user.getUserId(), id);
        return ResponseData.ok();
    }
    
    @Operation(summary = "Get Restaurant list with voting score", tags = "vote")
    @Cacheable(VOTE_CACHE)
    @GetMapping(value = "/vote/score", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseData<List<RestaurantScore>> getRestaurantsScore() {
        return ResponseData.of(voteService.getRestaurantsScore());
    }
    
}