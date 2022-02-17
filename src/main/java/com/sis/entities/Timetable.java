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

@Entity
@Getter
@Setter
@Table(name = "timetable")
public class Timetable extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "timetable_lecture_type",
            joinColumns = @JoinColumn(name = "lecture_type_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "timetable_id",
                    referencedColumnName = "id"))
    private Collection<LectureType> lectureType;

    @Column(name = "day")
    private String day;

    @Column(name = "start_time")
    private Float startTime;

    @Column(name = "end_time")
    private Float endTime;

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

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "timetable_faculty_member",
            joinColumns = @JoinColumn(name = "timetable_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "faculty_member_id",
                    referencedColumnName = "id"))
    private Collection<FacultyMember> facultyMember;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "timetable_course",
            joinColumns = @JoinColumn(name = "course_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "timetable_id",
                    referencedColumnName = "id"))
    private Collection<Course> course;

    @OneToMany
    @JoinColumn(name = "section_id", referencedColumnName = "id")
    private Collection<Section> section;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "timetable_building",
            joinColumns = @JoinColumn(name = "building_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "timetable_id",
                    referencedColumnName = "id"))
    private Collection<Building> building;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "timetable_classroom",
            joinColumns = @JoinColumn(name = "classroom_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "timetable_id",
                    referencedColumnName = "id"))
    private Collection<Classroom> classroom;

}
