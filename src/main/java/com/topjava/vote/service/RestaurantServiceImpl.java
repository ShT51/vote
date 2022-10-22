package com.topjava.vote.service;

import com.topjava.vote.model.dto.RestaurantDto;
import com.topjava.vote.model.entity.RestaurantEntity;
import com.topjava.vote.repository.RestaurantRepository;
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
public class RestaurantServiceImpl implements RestaurantService {
   
    private static final String ENTITY_NOT_FOUND_LOG = "No such restaurant with id: '{}'";
    
    private final RestaurantRepository restaurantRepository;
    private final DishService dishService;
    
    @Override
    @Transactional(readOnly = true)
    public RestaurantDto getRestaurant(long id) {
        return RestaurantDto.fromEntity(findWithDishes(id));
    }
    
    @Override
    @Transactional(readOnly = true)
    public RestaurantEntity getRestaurantEntityLazy(long id) {
        return findLazy(id);
    }
    
    @Override
    @Transactional(readOnly = true)
    public Set<RestaurantDto> getRestaurants() {
        return RestaurantDto.fromEntity(restaurantRepository.findAllWithDishes());
    }
    
    @Override
    public void saveRestaurant(RestaurantDto restaurantDto) {
        restaurantRepository.save(RestaurantDto.toEntity(restaurantDto));
    }
    
    @Override
    public RestaurantDto updateRestaurant(long id, RestaurantDto restaurantDto) {
        RestaurantEntity restaurantEntity = findWithDishes(id);
        restaurantEntity.setName(restaurantDto.getName());
        return RestaurantDto.fromEntity(restaurantEntity);
    }
    
    @Override
    public void deleteRestaurant(long id) {
        restaurantRepository.deleteById(getReferenceId(id));
    }
    
    @Override
    public RestaurantDto addToMenu(long id, List<Long> dishIds) {
        RestaurantEntity restaurant = findWithDishes(id);
        restaurant.addDishes(dishService.getDishEntities(dishIds));
        return RestaurantDto.fromEntity(restaurant);
    }
    
    @Override
    public RestaurantDto removeFromMenu(long id, List<Long> dishIds) {
        RestaurantEntity restaurant = findWithDishes(id);
        restaurant.removeDishes(dishService.getDishEntities(dishIds));
        return RestaurantDto.fromEntity(restaurant);
    }
    
    private RestaurantEntity findWithDishes(long id) {
        return restaurantRepository.findWithDishesById(id).orElseGet(() -> {
            log.error(ENTITY_NOT_FOUND_LOG, id);
            throw new NoSuchElementException();
        });
    }
    
    private RestaurantEntity findLazy(long id) {
        return restaurantRepository.findById(id).orElseGet(() -> {
            log.error(ENTITY_NOT_FOUND_LOG, id);
            throw new NoSuchElementException();
        });
    }
    
    private long getReferenceId(long id) {
        try {
            return restaurantRepository.getReferenceById(id).getId();
        } catch (NoSuchElementException ex) {
            log.error(ENTITY_NOT_FOUND_LOG, id);
            throw new NoSuchElementException();
        }
    }
    
}
