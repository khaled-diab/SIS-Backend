/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sis.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Collection;

@Entity
@Getter
@Setter
@Table(name = "lecture_type")
public class LectureType extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Column(name = "name_ar")
    private String nameAr;

    @Column(name = "name_en")
    private String nameEn;

    @ManyToMany(mappedBy = "lectureTypes")
    private Collection<Timetable> timetables;
}
