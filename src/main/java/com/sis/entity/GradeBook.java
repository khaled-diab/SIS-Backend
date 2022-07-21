/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sis.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "grade_book")
public class GradeBook extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ManyToOne
    @JoinColumn(name = "academic_term_id", referencedColumnName = "id")
    private AcademicTerm academicTerm;

    @ManyToOne
    @JoinColumn(name = "student_id", referencedColumnName = "id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "course_id", referencedColumnName = "id")
    private Course course;

//    @ManyToOne
//    @JoinColumn(name = "faculty_member_id", referencedColumnName = "id")
//    private FacultyMember facultyMember;

    @Column(name = "final_exam_grade")
    private Double finalExamGrade;

    @Column(name = "practical_grade")
    private Double practicalGrade;

    @Column(name = "oral_grade")
    private Double oralGrade;

    @Column(name = "mid_grade")
    private Double midGrade;

}
