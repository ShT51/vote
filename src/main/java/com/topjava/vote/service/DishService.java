package com.topjava.vote.service;

import com.topjava.vote.model.dto.DishDto;
import com.topjava.vote.model.entity.DishEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface DishService {
    
    DishDto getDishById(long id);
    List<DishDto> getDishes();
    
    @Transactional(readOnly = true)
    List<DishEntity> getDishEntities(List<Long> ids);
    
    void saveDish(DishDto newDish);
    
    DishDto updateDish(long id, DishDto dishDto);
    
    void deleteDish(long id);
    
    void softDeleteDish(long id);
}
