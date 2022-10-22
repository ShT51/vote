package com.topjava.vote.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.List;

@Data
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "role")
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class RoleEntity extends AbstractBaseNamedEntity implements GrantedAuthority {

    @Transient
    @ManyToMany(mappedBy = "roles")
    private List<UserEntity> users;
    
    @Override
    public String getAuthority() {
        return name;
    }
}