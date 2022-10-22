package com.topjava.vote.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "restaurant")
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class RestaurantEntity extends AbstractBaseNamedEntity {
    
    @OneToMany(mappedBy = "restaurant",
               cascade = CascadeType.ALL,
               orphanRemoval = true)
    private Set<DishEntity> dishes = new HashSet<>();
    
    @OneToMany(mappedBy = "restaurant",
               cascade = CascadeType.ALL,
               orphanRemoval = true)
    private Set<VoteEntity> votes = new HashSet<>();
    
    public void addDish(DishEntity dish) {
        dishes.add(dish);
        dish.setRestaurant(this);
    }
    
    public void removeDish(DishEntity dish) {
        dishes.remove(dish);
        dish.setRestaurant(null);
    }
}
