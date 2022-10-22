package com.topjava.vote.controller;

import com.topjava.vote.model.AuthenticatedUser;
import com.topjava.vote.model.response.ResponseData;
import com.topjava.vote.model.response.RestaurantScore;
import com.topjava.vote.service.VoteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class VoteController {
    
    private final VoteService voteService;
    
    @PostMapping("/vote")
    public ResponseData<Object> voteForRestaurant(@AuthenticationPrincipal AuthenticatedUser user,
                                                  @RequestParam(name = "restaurantId") long id) {
        voteService.vote(user.getUserId(), id);
        return ResponseData.ok();
    }
    
    @GetMapping(value = "/vote/winner", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseData<List<RestaurantScore>> getRestaurantScore() {
        return ResponseData.of(voteService.getRestaurantScore());
    }
    
}
