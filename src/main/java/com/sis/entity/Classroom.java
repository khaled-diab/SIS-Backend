/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sis.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "classroom")
@Getter
@Setter
@NoArgsConstructor
public class Classroom extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    @Column(name = "capacity")
    private int capacity;

    @Column(name = "status")
    private int status;

    @JoinColumn(name = "college_id", referencedColumnName = "id")
    @ManyToOne
    private College college;

    @JoinColumn(name = "building_id", referencedColumnName = "id")
    @ManyToOne
    private Building building;

    @OneToMany(mappedBy = "classroom")
    private Collection<Timetable> timetables;
}
