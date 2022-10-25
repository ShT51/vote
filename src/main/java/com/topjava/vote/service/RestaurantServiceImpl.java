package com.topjava.vote.service;

import com.topjava.vote.exception.VoteException;
import com.topjava.vote.model.dto.RestaurantDto;
import com.topjava.vote.model.entity.RestaurantEntity;
import com.topjava.vote.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {
   
    private static final String RESTAURANT_NOT_FOUND_LOG = "No such restaurant with id: '{}'";
    public static final String RESTAURANT_NOT_FOUND_ERROR = "Requested restaurant was not found";
    
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
    public List<RestaurantDto> getRestaurants() {
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
        restaurantRepository.deleteById(validateId(id));
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
            log.error(RESTAURANT_NOT_FOUND_LOG, id);
            throw VoteException.badRequest(RESTAURANT_NOT_FOUND_ERROR);
        });
    }
    
    private RestaurantEntity findLazy(long id) {
        return restaurantRepository.findById(id).orElseGet(() -> {
            log.error(RESTAURANT_NOT_FOUND_LOG, id);
            throw VoteException.badRequest(RESTAURANT_NOT_FOUND_ERROR);
        });
    }
    
    private long validateId(long id) {
        return restaurantRepository.checkId(id).orElseGet(() -> {
            log.error(RESTAURANT_NOT_FOUND_LOG, id);
            throw VoteException.badRequest(RESTAURANT_NOT_FOUND_ERROR);
        });
    }
    
}
