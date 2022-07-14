package com.sis.entity.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sis.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Getter
@Setter
@Table(name = "role_screen")
@AllArgsConstructor
@NoArgsConstructor
public class RoleScreen extends BaseEntity {

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    private Role role;

    @ManyToOne
    @JoinColumn(name = "screen_id", referencedColumnName = "id")
    private Screen screen;
}
