package com.topjava.vote.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Set;

@Data
@Entity
@SuperBuilder
@DynamicUpdate
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "dish")
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper=true, exclude = {"restaurants"})
public class DishEntity extends AbstractBaseNamedEntity {

    @Column(name = "price", nullable = false)
    private double price;
    
    @ManyToMany(mappedBy = "dishes")
    private Set<RestaurantEntity> restaurants;
    
    @Column(name = "available", nullable = false, columnDefinition = "bool default true")
    private boolean available = true;
}