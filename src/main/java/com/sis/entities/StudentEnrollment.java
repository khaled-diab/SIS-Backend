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
@Table(name = "student_enrollment")
public class StudentEnrollment extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ManyToOne
    @JoinColumn(name = "academic_year_id", referencedColumnName = "id")
    private AcademicYear academicYear;

    @ManyToOne
    @JoinColumn(name = "academic_term_id", referencedColumnName = "id")
    private AcademicTerm academicTerm;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "student_enrollment_student",
            joinColumns = @JoinColumn(name = "student_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "student_enrollment_id",
                    referencedColumnName = "id"))
    private Collection<Student> student;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "student_enrollment_course",
            joinColumns = @JoinColumn(name = "course_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "student_enrollment_id",
                    referencedColumnName = "id"))
    private Collection<Course> course;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "student_enrollment_section",
            joinColumns = @JoinColumn(name = "section_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "student_enrollment_id",
                    referencedColumnName = "id"))
    private Collection<Section> section;

}
