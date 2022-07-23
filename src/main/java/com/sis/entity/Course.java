/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sis.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "course")
public class Course extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Column(name = "code")
    private String code;

    @Column(name = "name_ar", unique = true)
    private String nameAr;

    @Column(name = "name_en")
    private String nameEn;

    @Column(name = "theoretical_hours", columnDefinition = "varchar(255) default '0'")
    private Float theoreticalHours;

    @Column(name = "exercises_hours", columnDefinition = "varchar(255) default '0'")
    private Float exercisesHours;

    @Column(name = "practical_hours", columnDefinition = "varchar(255) default '0'")
    private Float practicalHours;

    @Column(name = "total_hours", columnDefinition = "varchar(255) default '0'")
    private Float totalHours;

    @Column(name = "weeks", columnDefinition = "varchar(255) default '0'")
    private Integer weeks;

    @Column(name = "final_grade", columnDefinition = "varchar(255) default '0'")
    private Float finalGrade;

    @Column(name = "final_exam_grade", columnDefinition = "varchar(255) default '0'")
    private Float finalExamGrade;

    @Column(name = "practical_grade", columnDefinition = "varchar(255) default '0'")
    private Float practicalGrade;

    @Column(name = "oral_grade", columnDefinition = "varchar(255) default '0'")
    private Float oralGrade;

    @Column(name = "mid_grade", columnDefinition = "varchar(255) default '0'")
    private Float midGrade;

    @ManyToOne
    @JoinColumn(name = "collegeId", referencedColumnName = "id")
    private College college;

    @ManyToOne
    @JoinColumn(name = "departmentId", referencedColumnName = "id")
    private Department department;

    @OneToMany(mappedBy = "courseId")
    private List<Lecture> lectures;

    @OneToMany(mappedBy = "course")
    private Collection<Timetable> timetables;

    @OneToMany(mappedBy = "course")
    private Collection<StudentEnrollment> studentEnrollments;

    @OneToMany(mappedBy = "course")
    private Collection<Section> studentSections;


}
