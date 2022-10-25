package com.topjava.vote.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Data
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "restaurant")
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true,  exclude = {"dishes"})
public class RestaurantEntity extends AbstractBaseNamedEntity {
    
    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "restaurant_dish",
               joinColumns = @JoinColumn(name = "restaurant_id"),
               inverseJoinColumns = @JoinColumn(name = "dish_id")
    )
    private Set<DishEntity> dishes;
    
    public void addDishes(Collection<DishEntity> newDishes) {
        dishes.addAll(newDishes);
        dishes.forEach(dish -> dish.getRestaurants().add(this));
    }
    
    public void removeDishes(List<DishEntity> dishesForRemove) {
        dishesForRemove.forEach(dishes::remove);
        dishesForRemove.forEach(dish -> dish.getRestaurants().remove(this));
    }
    
}
