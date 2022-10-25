package com.topjava.vote.service;

import com.topjava.vote.exception.VoteException;
import com.topjava.vote.model.entity.RestaurantEntity;
import com.topjava.vote.model.entity.UserEntity;
import com.topjava.vote.model.entity.VoteEntity;
import com.topjava.vote.model.response.RestaurantScore;
import com.topjava.vote.repository.VoteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class VoteServiceImpl implements VoteService {
    
    public static final LocalTime VOTE_DEADLINE = LocalTime.of(11, 0);
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
        return LocalTime.now(Clock.systemUTC()).isBefore(VOTE_DEADLINE);
    }
}
