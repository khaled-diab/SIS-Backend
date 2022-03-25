package com.sis.entities.security;

import com.sis.entities.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name = "screen")
@AllArgsConstructor
@NoArgsConstructor
public class Screen extends BaseEntity {

    private String name;
    private boolean allowEdit;
    private boolean allowDelete;
}
