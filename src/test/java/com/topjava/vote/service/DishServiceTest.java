package com.topjava.vote.service;

import com.topjava.vote.exception.VoteException;
import com.topjava.vote.model.dto.DishDto;
import com.topjava.vote.model.entity.DishEntity;
import com.topjava.vote.repository.DishRepository;
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
class DishServiceTest {
   
    @Mock
    private DishRepository dishRepository;
    @InjectMocks
    private DishServiceImpl dishService;
    
    @ParameterizedTest
    @MethodSource("com.topjava.vote.argument.provider.DishServiceArgumentsProvider#getDishByIdArgs")
    void getDishById(DishEntity entity, DishDto expected) {
        when(dishRepository.findById(anyLong())).thenReturn(of(entity));
        assertEquals(expected, dishService.getDishById(1L));
    }
    
    @Test
    void getDishById_fail() {
        when(dishRepository.findById(anyLong())).thenReturn(empty());
        assertThrows(VoteException.class, () -> dishService.getDishById(1L));
    }
    
    @ParameterizedTest
    @MethodSource("com.topjava.vote.argument.provider.DishServiceArgumentsProvider#getDishes")
    void getDishes(List<DishEntity> entities, List<DishDto> expected) {
        when(dishRepository.findAll()).thenReturn(entities);
        assertEquals(expected, dishService.getDishes());
    }
    
    @ParameterizedTest
    @MethodSource("com.topjava.vote.argument.provider.DishServiceArgumentsProvider#getDishEntities")
    void getDishEntities(List<DishEntity> expected) {
        when(dishRepository.findAllById(anyList())).thenReturn(expected);
        assertEquals(expected, dishService.getDishEntities(List.of(1L)));
    }
    
    @ParameterizedTest
    @MethodSource("com.topjava.vote.argument.provider.DishServiceArgumentsProvider#saveDish")
    void saveDish(DishEntity entity, DishDto dto) {
        when(dishRepository.save(any())).thenReturn(entity);
        assertDoesNotThrow(() -> dishService.saveDish(dto));
    }
    
    @ParameterizedTest
    @MethodSource("com.topjava.vote.argument.provider.DishServiceArgumentsProvider#updateDish")
    void updateDish(DishEntity oldEntity, DishDto expected) {
        when(dishRepository.findById(anyLong())).thenReturn(of(oldEntity));
        assertEquals(expected, dishService.updateDish(1L, expected));
    }
    
    @ParameterizedTest
    @MethodSource("com.topjava.vote.argument.provider.DishServiceArgumentsProvider#updateDish")
    void updateDish_fail(DishEntity oldEntity, DishDto expected) {
        when(dishRepository.findById(anyLong())).thenReturn(empty());
        assertThrows(VoteException.class, () -> dishService.updateDish(1L, expected));
    }
    
    @Test
    void deleteDish() {
        when(dishRepository.checkId(anyLong())).thenReturn(of(1L));
        doNothing().when(dishRepository).deleteById(anyLong());
        assertDoesNotThrow(() -> dishService.deleteDish(1L));
    }
    
    @Test
    void deleteDish_fail() {
        when(dishRepository.checkId(anyLong())).thenReturn(empty());
        assertThrows(VoteException.class, () -> dishService.deleteDish(1L));
        
    }
    
    @Test
    void softDeleteDish() {
        when(dishRepository.checkId(anyLong())).thenReturn(of(1L));
        doNothing().when(dishRepository).softDelete(anyLong());
        assertDoesNotThrow(() -> dishService.softDeleteDish(1L));
    }
    
    @Test
    void softDeleteDish_fail() {
        when(dishRepository.checkId(anyLong())).thenReturn(empty());
        assertThrows(VoteException.class, () -> dishService.softDeleteDish(1L));
    }
}