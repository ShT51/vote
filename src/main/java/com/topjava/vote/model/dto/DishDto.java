package com.topjava.vote.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.topjava.vote.model.entity.DishEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.READ_ONLY;
import static java.util.Collections.emptySet;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DishDto {
    
    @JsonProperty(access = READ_ONLY)
    private long id;
    
    @NotBlank
    private String name;
    
    @Positive
    private double price;
    
    @JsonProperty(access = READ_ONLY)
    private boolean availability = true;
    
    public static DishDto fromEntity(DishEntity entity) {
        if (entity == null) {
            return null;
        }
        return DishDto.builder()
                      .id(entity.getId())
                      .name(entity.getName())
                      .price(entity.getPrice())
                      .availability(entity.isAvailable())
                      .build();
    }
    
    public static DishEntity toEntity(DishDto dto) {
        if (dto == null) {
            return null;
        }
        return DishEntity.builder()
                         .name(dto.getName())
                         .price(dto.getPrice())
                         .available(true)
                         .build();
    }
    
    public static Set<DishDto> fromEntity(Collection<DishEntity> entities) {
        if (entities == null) {
            return emptySet();
        }
        Set<DishDto> resultList = new HashSet<>();
        entities.forEach(e -> resultList.add(fromEntity(e)));
        return resultList;
    }
    
}