package com.topjava.vote.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
@EqualsAndHashCode(callSuper=true)
public abstract class AbstractBaseNamedEntity extends AbstractBaseEntity {
    
    @Column(name = "name", nullable = false)
    protected String name;
}
