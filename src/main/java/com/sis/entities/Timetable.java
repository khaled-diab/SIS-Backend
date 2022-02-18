/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sis.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.Collection;

@Entity
@Getter
@Setter
@Table(name = "timetable")
public class Timetable extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ManyToOne
    @JoinColumn(name = "lecture_type_id", referencedColumnName = "id")
    private LectureType lectureType;

    @Column(name = "day")
    private String day;

    @Column(name = "start_time")
    private LocalTime startTime;

    @Column(name = "end_time")
    private LocalTime endTime;

    @ManyToOne
    @JoinColumn(name = "college_id", referencedColumnName = "id")
    private College college;

    @ManyToOne
    @JoinColumn(name = "department_id", referencedColumnName = "id")
    private Department department;

    @ManyToOne
    @JoinColumn(name = "academic_year_id", referencedColumnName = "id")
    private AcademicYear academicYear;

    @ManyToOne
    @JoinColumn(name = "academic_term_id", referencedColumnName = "id")
    private AcademicTerm academicTerm;

    @ManyToOne
    @JoinColumn(name = "faculty_member_id", referencedColumnName = "id")
    private FacultyMember facultyMember;

    @ManyToOne
    @JoinColumn(name = "course_id", referencedColumnName = "id")
    private Course course;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "timetable_section",
            joinColumns = @JoinColumn(name = "timetable_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "section_id",
                    referencedColumnName = "id"))
    private Collection<Section> sections;

    @ManyToOne
    @JoinColumn(name = "building_id", referencedColumnName = "id")
    private Building building;

    @ManyToOne
    @JoinColumn(name = "classroom_id", referencedColumnName = "id")
    private Classroom classroom;

}
