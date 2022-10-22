package com.topjava.vote.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;

@Data
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "role")
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class RoleEntity extends AbstractBaseNamedEntity {

    @ManyToMany(mappedBy = "actualRoles")
    private List<UserEntity> associatedUsers;
}
