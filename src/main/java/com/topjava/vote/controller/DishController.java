package com.topjava.vote.controller;

import com.topjava.vote.model.dto.DishDto;
import com.topjava.vote.model.response.ResponseData;
import com.topjava.vote.service.DishService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@Slf4j
@RestController
@RequiredArgsConstructor
public class DishController {
    private final DishService dishService;
    
    @GetMapping(value = "admin/dishes/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseData<DishDto> getDish(@PathVariable long id) {
        return ResponseData.of(dishService.getDishById(id));
    }
    
    @GetMapping(value = "admin/dishes", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseData<Set<DishDto>> getDishes() {
        return ResponseData.of(dishService.getDishes());
    }
    
    @PostMapping(value = "admin/dishes", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseData<Object> createDish(@RequestBody DishDto dishDto) {
        dishService.saveDish(dishDto);
        return ResponseData.ok();
    }
    
    @PutMapping(value = "admin/dishes/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseData<DishDto> updateDish(@PathVariable long id, @RequestBody DishDto dishDto) {
        return ResponseData.of(dishService.updateDish(id, dishDto));
    }
    
    @DeleteMapping(value = "admin/dishes/{id}/hard", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseData<Object> deleteDish(@PathVariable long id) {
        try {
            dishService.deleteDish(id);
        } catch (Exception e) {
            log.error("Try to remove dish: '{}'. Remove dish from restaurant", id);
            throw new UnsupportedOperationException();
        }
        return ResponseData.ok();
    }
    
    @DeleteMapping(value = "admin/dishes/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseData<Object> softDeleteDish(@PathVariable long id) {
        dishService.softDeleteDish(id);
        return ResponseData.ok();
    }
}