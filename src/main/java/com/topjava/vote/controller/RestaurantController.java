package com.topjava.vote.controller;

import com.topjava.vote.model.dto.RestaurantDto;
import com.topjava.vote.model.response.ResponseData;
import com.topjava.vote.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@RestController
@RequiredArgsConstructor
public class RestaurantController {
    private final RestaurantService restaurantService;
    
    @GetMapping(value = "restaurants/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseData<RestaurantDto> getRestaurant(@PathVariable long id) {
        return ResponseData.of(restaurantService.getRestaurant(id));
    }
    
    @GetMapping(value = "restaurants", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseData<Set<RestaurantDto>> getRestaurants() {
        return ResponseData.of(restaurantService.getRestaurants());
    }
    
    @PostMapping(value = "admin/restaurants", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseData<Object> createRestaurant(@RequestBody @Valid RestaurantDto restaurantDto) {
        restaurantService.saveRestaurant(restaurantDto);
        return ResponseData.ok();
    }
    
    @PutMapping(value = "admin/restaurants/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseData<RestaurantDto> updateRestaurant(@PathVariable long id, @RequestBody @Valid RestaurantDto restaurantDto) {
        return ResponseData.of(restaurantService.updateRestaurant(id, restaurantDto));
    }
    
    @DeleteMapping(value = "admin/restaurants/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseData<Object> deleteRestaurant(@PathVariable long id) {
        restaurantService.deleteRestaurant(id);
        return ResponseData.ok();
    }
    
    @PostMapping(value = "admin/restaurants/{id}/dishes", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseData<RestaurantDto> addToMenu(@PathVariable long id, @RequestBody List<Long> dishIds) {
        return ResponseData.of(restaurantService.addToMenu(id, dishIds));
    }
    
    @DeleteMapping(value = "admin/restaurants/{id}/dishes", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseData<RestaurantDto> removeFromMenu(@PathVariable long id, @RequestBody List<Long> dishIds) {
        return ResponseData.of(restaurantService.removeFromMenu(id, dishIds));
    }
}