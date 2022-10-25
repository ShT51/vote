package com.topjava.vote.service;

import com.topjava.vote.exception.VoteException;
import com.topjava.vote.model.entity.RestaurantEntity;
import com.topjava.vote.model.entity.UserEntity;
import com.topjava.vote.model.entity.VoteEntity;
import com.topjava.vote.model.response.RestaurantScore;
import com.topjava.vote.repository.VoteRepository;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Slf4j
@Service
@Transactional
public class VoteServiceImpl implements VoteService {
    
    public VoteServiceImpl(@NonNull UserService userService,
                           @NonNull RestaurantService restaurantService,
                           @NonNull VoteRepository voteRepository,
                           @Value("${vote.deadlineHours}") int hours) {
        
        this.userService = userService;
        this.restaurantService = restaurantService;
        this.voteRepository = voteRepository;
        this.voteDeadLine = LocalTime.of(hours, 0);
    }
    
    private final LocalTime voteDeadLine;
    private final UserService userService;
    private final RestaurantService restaurantService;
    private final VoteRepository voteRepository;
    
    
    @Override
    public void vote(long id, long restaurantId) {
        UserEntity userEntity = userService.getUserById(id);
        RestaurantEntity restaurantEntity = restaurantService.getRestaurantEntityLazy(restaurantId);
        voteRepository.findUserVoteForToday(id)
                      .ifPresentOrElse(vote -> {
                          if (isRevotingAvailable()) {
                              vote.setRestaurant(restaurantEntity);
                          } else {
                              log.warn("User: '{}' try to re-vote after the deadline", id);
                              throw VoteException.badRequest("Sorry, too late to change your mind");
                          }
                      }, () -> voteRepository.save(new VoteEntity(LocalDate.now(), userEntity, restaurantEntity)));
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<RestaurantScore> getRestaurantsScore() {
        return voteRepository.findRestaurantsWithScore();
    }
    
    private boolean isRevotingAvailable() {
        return LocalTime.now(Clock.systemUTC()).isBefore(voteDeadLine);
    }
}
