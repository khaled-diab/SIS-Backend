/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sis.entities;

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

    @Column(name = "name_ar")
    private String name_ar;

    @Column(name = "name_en")
    private String name_en;

    @Column(name = "capacity")
    private int capacity;

    @Column(name = "status")
    private int status;

    @JoinColumn(name = "department", referencedColumnName = "id")
    @ManyToOne
    private Department department;

    @JoinColumn(name = "building_id", referencedColumnName = "id")
    @ManyToOne
    private Building building;

    @ManyToMany(mappedBy = "classrooms")
    private Collection<Timetable> timetables;
}
