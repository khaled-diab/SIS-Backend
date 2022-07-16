package com.sis.entity.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sis.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
@Table(name = "role")
public class Role extends BaseEntity implements GrantedAuthority {

    private static final long serialVersionUID = 1L;


    @Column(name = "role_name")
    private String roleName;

    @Column(name = "description")
    private String description;

    @OneToMany(
            mappedBy = "role",
            cascade = CascadeType.ALL,
            targetEntity = RoleScreen.class
    )
    private List<RoleScreen> roleScreens = new ArrayList<>();


    @Override
    @JsonIgnore
    public String getAuthority() {
        return roleName;
    }

}
