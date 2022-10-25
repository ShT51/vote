package com.topjava.vote.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.topjava.vote.model.entity.RestaurantEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.READ_ONLY;
import static java.util.Collections.emptyList;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantDto {
    
    @JsonProperty(access = READ_ONLY)
    private long id;
    
    @NotBlank
    private String name;
    
    @JsonProperty(access = READ_ONLY)
    private Set<DishDto> dishes;
    
    public static RestaurantDto fromEntity(RestaurantEntity entity) {
        if (entity == null) {
            return null;
        }
        return RestaurantDto.builder()
                            .id(entity.getId())
                            .name(entity.getName())
                            .dishes(new HashSet<>(DishDto.fromEntity(entity.getDishes())))
                            .build();
    }
    
    public static RestaurantEntity toEntity(RestaurantDto dto) {
        if (dto == null) {
            return null;
        }
        return RestaurantEntity.builder()
                               .name(dto.getName())
                               .build();
    }
    
    public static List<RestaurantDto> fromEntity(Collection<RestaurantEntity> entities) {
        if (entities == null) {
            return emptyList();
        }
        List<RestaurantDto> resultList = new ArrayList<>();
        entities.forEach(e -> resultList.add(fromEntity(e)));
        return resultList;
    }
}
