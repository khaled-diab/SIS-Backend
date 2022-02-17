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
@Table(name = "faculty_member_enrollment")
public class FacultyMemberEnrollment extends BaseEntity {

    private static final long serialVersionUID = 1L;

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
    @JoinTable(name = "faculty_member_enrollment_faculty_member",
            joinColumns = @JoinColumn(name = "faculty_member_enrollment_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "faculty_member_id",
                    referencedColumnName = "id"))
    private Collection<FacultyMember> facultyMembers;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "faculty_member_enrollment_course",
            joinColumns = @JoinColumn(name = "faculty_member_enrollment_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "course_id",
                    referencedColumnName = "id"))
    private Collection<Course> courses;

//    @ManyToMany(cascade = CascadeType.ALL)
//    @JoinTable(name = "faculty_member_enrollment_section",
//            joinColumns = @JoinColumn(name = "faculty_member_enrollment_id", referencedColumnName = "id"),
//            inverseJoinColumns = @JoinColumn(name = "section_id",
//                    referencedColumnName = "id"))
//    private Collection<Section> sections;

}
