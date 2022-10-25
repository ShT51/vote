package com.topjava.vote.service;

import com.topjava.vote.exception.VoteException;
import com.topjava.vote.model.dto.DishDto;
import com.topjava.vote.model.entity.DishEntity;
import com.topjava.vote.repository.DishRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class DishServiceImpl implements DishService {
   
    public static final String DISH_NOT_FOUND_ERROR = "Requested dish was not found";
    
    private final DishRepository repository;
    
    @Override
    @Transactional(readOnly = true)
    public DishDto getDishById(long id) {
        return DishDto.fromEntity(findEntity(id));
    }
    
    @Override
    @Transactional(readOnly = true)
    public Set<DishDto> getDishes() {
        return DishDto.fromEntity(repository.findAll());
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<DishEntity> getDishEntities(List<Long> ids) {
        List<DishEntity> resultList = repository.findAllById(ids);
        if (resultList.isEmpty()) {
            log.error("Dishes with ids: '{}' not found", ids);
            throw VoteException.badRequest(DISH_NOT_FOUND_ERROR);
        }
        return resultList;
    }
    
    @Override
    public void saveDish(DishDto dishDto) {
        repository.save(DishDto.toEntity(dishDto));
    }
    
    @Override
    public DishDto updateDish(long id, DishDto dishDto) {
        DishEntity dishEntity = findEntity(id);
        dishEntity.setPrice(dishDto.getPrice());
        dishEntity.setName(dishDto.getName());
        return DishDto.fromEntity(dishEntity);
    }
    
    @Override
    public void deleteDish(long id) {
        repository.deleteById(getReferenceId(id));
    }
    
    @Override
    public void softDeleteDish(long id) {
        repository.softDelete(getReferenceId(id));
    }
    
    private DishEntity findEntity(long id) {
        return repository.findById(id).orElseGet(() -> {
            log.error("No such dish with id: '{}'", id);
            throw VoteException.badRequest(DISH_NOT_FOUND_ERROR);
        });
    }
    
    private long getReferenceId(long id) {
        try {
            return repository.getReferenceById(id).getId();
        } catch (NoSuchElementException ex) {
            log.error("No such dish with id: '{}'", id);
            throw VoteException.badRequest(DISH_NOT_FOUND_ERROR);
        }
    }
    
}