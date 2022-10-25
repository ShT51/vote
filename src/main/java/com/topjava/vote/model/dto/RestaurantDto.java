package com.topjava.vote.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.topjava.vote.model.entity.RestaurantEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.READ_ONLY;
import static java.util.Collections.emptySet;

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
    private Set<DishDto> dishes = new HashSet<>();
    
    public static RestaurantDto fromEntity(RestaurantEntity entity) {
        if (entity == null) {
            return null;
        }
        return RestaurantDto.builder()
                            .id(entity.getId())
                            .name(entity.getName())
                            .dishes(DishDto.fromEntity(entity.getDishes()))
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
    
    public static Set<RestaurantDto> fromEntity(Collection<RestaurantEntity> entities) {
        if (entities == null) {
            return emptySet();
        }
        Set<RestaurantDto> resultList = new HashSet<>();
        entities.forEach(e -> resultList.add(fromEntity(e)));
        return resultList;
    }
}
