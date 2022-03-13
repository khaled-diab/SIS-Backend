/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sis.entities;


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

    @Column(name = "theoretical_hours")
    private Float theoreticalHours;

    @Column(name = "exercises_hours")
    private Float exercisesHours;

    @Column(name = "practical_hours")
    private Float practicalHours;

    @Column(name = "total_hours")
    private Float totalHours;

    @Column(name = "weeks")
    private Integer weeks;

    @Column(name = "final_grade")
    private Float finalGrade;

    @Column(name = "final_exam_grade")
    private Float finalExamGrade;

    @Column(name = "practical_grade")
    private Float practicalGrade;

    @Column(name = "oral_grade")
    private Float oralGrade;

    @Column(name = "mid_grade")
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
    private Collection<FacultyMemberEnrollment> facultyMemberEnrollments;

    @OneToMany(mappedBy = "course")
    private Collection<StudentEnrollment> studentEnrollments;

    @OneToMany(mappedBy = "course")
    private Collection<Section> studentSections;

    @OneToMany(mappedBy = "course")
    private Collection<AttendanceDetails> attendanceDetails;
}
