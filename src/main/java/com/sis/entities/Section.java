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
@Table(name = "section")
public class Section extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Column(name = "section_number")
    private String sectionNumber;

    @Column(name = "theoretical_lectures")
    private int theoreticalLectures;

    @Column(name = "practical_lectures")
    private int practicalLectures;

    @Column(name = "exercises_lectures")
    private int exercisesLectures;

    @ManyToOne
    @JoinColumn(name = "major_id", referencedColumnName = "id")
    private Major major;

    @ManyToOne
    @JoinColumn(name = "study_type_id", referencedColumnName = "id")
    private StudyType studyType;

    @ManyToOne
    @JoinColumn(name = "college_id", referencedColumnName = "id")
    private College college;

    @ManyToOne
    @JoinColumn(name = "department_id", referencedColumnName = "id")
    private Department department;

    @ManyToOne
    @JoinColumn(name = "academic_year", referencedColumnName = "id")
    private AcademicYear academicYear;

    @JoinColumn(name = "academic_term", referencedColumnName = "id")
    @ManyToOne
    private AcademicTerm academicTerm;


    @JoinColumn(name = "course_id", referencedColumnName = "id")
    @ManyToOne
    private Course course;

    @OneToMany(mappedBy = "section")
    private Collection<StudentEnrollment> studentEnrollments;

    @OneToMany(mappedBy = "section")
    private Collection<Timetable> timetables;

    @OneToMany(mappedBy = "section",fetch = FetchType.LAZY)
    private List<Lecture> lectures;

    @OneToMany(mappedBy = "section")
    private Collection<AttendanceDetails> attendanceDetails;


}
