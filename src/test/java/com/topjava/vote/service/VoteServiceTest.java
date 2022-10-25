package com.topjava.vote.service;

import com.topjava.vote.exception.VoteException;
import com.topjava.vote.model.entity.RestaurantEntity;
import com.topjava.vote.model.entity.UserEntity;
import com.topjava.vote.model.entity.VoteEntity;
import com.topjava.vote.model.response.RestaurantScore;
import com.topjava.vote.repository.VoteRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Clock;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static java.util.Optional.empty;
import static java.util.Optional.of;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class VoteServiceTest {
    
    @Mock
    private UserService userService;
    @Mock
    private RestaurantService restaurantService;
    @Mock
    private VoteRepository voteRepository;
    private VoteService voteService;
    
    
    @ParameterizedTest
    @MethodSource("com.topjava.vote.argument.provider.VoteServiceArgumentsProvider#vote")
    void vote_revotingAvailable(UserEntity userEntity, RestaurantEntity restaurantEntity, VoteEntity voteEntity) {
        int testDeadLine = LocalTime.now(Clock.systemUTC()).plus(1, ChronoUnit.HOURS).getHour();
        voteService = spy(new VoteServiceImpl(userService, restaurantService, voteRepository, testDeadLine));
        
        when(userService.getUserById(anyLong())).thenReturn(userEntity);
        when(restaurantService.getRestaurantEntityLazy(anyLong())).thenReturn(restaurantEntity);
        when(voteRepository.findUserVoteForToday(anyLong())).thenReturn(of(voteEntity));
        
        assertDoesNotThrow(() -> voteService.vote(1L, 1L));
    }
    
    @ParameterizedTest
    @MethodSource("com.topjava.vote.argument.provider.VoteServiceArgumentsProvider#vote")
    void vote_revotingNotAvailable(UserEntity userEntity, RestaurantEntity restaurantEntity, VoteEntity voteEntity) {
        int testDeadLine = LocalTime.now(Clock.systemUTC()).minus(1, ChronoUnit.HOURS).getHour();
        voteService = spy(new VoteServiceImpl(userService, restaurantService, voteRepository, testDeadLine));
        
        when(userService.getUserById(anyLong())).thenReturn(userEntity);
        when(restaurantService.getRestaurantEntityLazy(anyLong())).thenReturn(restaurantEntity);
        when(voteRepository.findUserVoteForToday(anyLong())).thenReturn(of(voteEntity));
        
        assertThrows(VoteException.class, () -> voteService.vote(1L, 1L));
    }
    
    @ParameterizedTest
    @MethodSource("com.topjava.vote.argument.provider.VoteServiceArgumentsProvider#vote")
    void vote_new(UserEntity userEntity, RestaurantEntity restaurantEntity, VoteEntity voteEntity) {
        voteService = spy(new VoteServiceImpl(userService, restaurantService, voteRepository, 0));
        
        when(userService.getUserById(anyLong())).thenReturn(userEntity);
        when(restaurantService.getRestaurantEntityLazy(anyLong())).thenReturn(restaurantEntity);
        when(voteRepository.findUserVoteForToday(anyLong())).thenReturn(empty());
        when(voteRepository.save(any())).thenReturn(voteEntity);
    
        assertDoesNotThrow(() -> voteService.vote(1L, 1L));
    }
    
    @ParameterizedTest
    @MethodSource("com.topjava.vote.argument.provider.VoteServiceArgumentsProvider#getRestaurantsScore")
    void getRestaurantsScore(List<RestaurantScore> expected) {
        voteService = spy(new VoteServiceImpl(userService, restaurantService, voteRepository, 0));
        
        when(voteRepository.findRestaurantsWithScore()).thenReturn(expected);
        assertEquals(expected, voteService.getRestaurantsScore());
    }
}