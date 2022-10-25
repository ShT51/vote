package com.topjava.vote.service;

import com.topjava.vote.exception.VoteException;
import com.topjava.vote.model.dto.RestaurantDto;
import com.topjava.vote.model.entity.DishEntity;
import com.topjava.vote.model.entity.RestaurantEntity;
import com.topjava.vote.repository.RestaurantRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static java.util.Optional.empty;
import static java.util.Optional.of;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RestaurantServiceTest {
    
    @Mock
    private RestaurantRepository repository;
    @Mock
    private DishService dishService;
    @InjectMocks
    private RestaurantServiceImpl service;
    
    @ParameterizedTest
    @MethodSource("com.topjava.vote.argument.provider.RestaurantServiceArgumentsProvider#getRestaurant")
    void getRestaurant(RestaurantEntity entity, RestaurantDto expected) {
        when(repository.findWithDishesById(anyLong())).thenReturn(of(entity));
        assertEquals(expected, service.getRestaurant(1L));
    }
    
    @Test
    void getRestaurant_fail() {
        when(repository.findWithDishesById(anyLong())).thenReturn(empty());
        assertThrows(VoteException.class, () -> service.getRestaurant(1L));
    }
    
    @ParameterizedTest
    @MethodSource("com.topjava.vote.argument.provider.RestaurantServiceArgumentsProvider#getRestaurantEntityLazy")
    void getRestaurantEntityLazy(RestaurantEntity expected) {
        when(repository.findById(anyLong())).thenReturn(of(expected));
        assertEquals(expected, service.getRestaurantEntityLazy(1L));
    }
    
    @Test
    void getRestaurantEntityLazy_fail() {
        when(repository.findById(anyLong())).thenReturn(empty());
        assertThrows(VoteException.class, () -> service.getRestaurantEntityLazy(1L));
    }
    
    @ParameterizedTest
    @MethodSource("com.topjava.vote.argument.provider.RestaurantServiceArgumentsProvider#getRestaurants")
    void getRestaurants(List<RestaurantEntity> entities, List<RestaurantDto> expected) {
        when(repository.findAllWithDishes()).thenReturn(entities);
        assertEquals(expected, service.getRestaurants());
    }
    
    @ParameterizedTest
    @MethodSource("com.topjava.vote.argument.provider.RestaurantServiceArgumentsProvider#saveRestaurant")
    void saveRestaurant(RestaurantEntity entity, RestaurantDto dto) {
        when(repository.save(any())).thenReturn(entity);
        assertDoesNotThrow(() -> service.saveRestaurant(dto));
    }
    
    @ParameterizedTest
    @MethodSource("com.topjava.vote.argument.provider.RestaurantServiceArgumentsProvider#updateRestaurant")
    void updateRestaurant(RestaurantEntity oldEntity, RestaurantDto expected) {
        when(repository.findWithDishesById(anyLong())).thenReturn(of(oldEntity));
        assertEquals(expected, service.updateRestaurant(1L, expected));
    }
    
    @Test
    void updateRestaurant_fail() {
        when(repository.findWithDishesById(anyLong())).thenReturn(empty());
        assertThrows(VoteException.class, () -> service.updateRestaurant(1L, any()));
    }
    
    @Test
    void deleteRestaurant() {
        when(repository.checkId(anyLong())).thenReturn(of(1L));
        doNothing().when(repository).deleteById(1L);
        assertDoesNotThrow(() -> service.deleteRestaurant(1L));
    }
    
    @Test
    void deleteRestaurant_fail() {
        when(repository.checkId(anyLong())).thenReturn(empty());
        assertThrows(VoteException.class, () -> service.deleteRestaurant(1L));
    }
    
    @ParameterizedTest
    @MethodSource("com.topjava.vote.argument.provider.RestaurantServiceArgumentsProvider#addToMenu")
    void addToMenu(RestaurantEntity entity, List<DishEntity> dishListEntity, RestaurantDto expected) {
        when(repository.findWithDishesById(anyLong())).thenReturn(of(entity));
        when(dishService.getDishEntities(anyList())).thenReturn(dishListEntity);
        assertEquals(expected, service.addToMenu(1L, List.of(2L)));
    }
    
    @ParameterizedTest
    @MethodSource("com.topjava.vote.argument.provider.RestaurantServiceArgumentsProvider#removeFromMenu")
    void removeFromMenu(RestaurantEntity entity, List<DishEntity> dishListEntity, RestaurantDto expected) {
        when(repository.findWithDishesById(anyLong())).thenReturn(of(entity));
        when(dishService.getDishEntities(anyList())).thenReturn(dishListEntity);
        assertEquals(expected, service.removeFromMenu(1L, List.of(1L)));
    }
}