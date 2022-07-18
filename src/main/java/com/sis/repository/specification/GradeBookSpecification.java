package com.sis.repository.specification;

import com.sis.entity.*;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;

public class GradeBookSpecification implements Specification<GradeBook> {

    private final Long filterAcademicTerm;

    private final Long filterCourse;

    private final Long filterStudent;

    private final Long filterFacultyMember;


    public GradeBookSpecification(Long filterAcademicTerm, Long filterCourse, Long filterStudent, Long filterFacultyMember) {
        this.filterAcademicTerm = filterAcademicTerm;
        this.filterCourse = filterCourse;
        this.filterStudent = filterStudent;
        this.filterFacultyMember = filterFacultyMember;
    }

    public GradeBookSpecification() {
        this.filterAcademicTerm = null;
        this.filterCourse = null;
        this.filterStudent = null;
        this.filterFacultyMember = null;
    }

    @Override
    public Specification<GradeBook> and(Specification<GradeBook> other) {
        return Specification.super.and(other);
    }

    @Override
    public Specification<GradeBook> or(Specification<GradeBook> other) {
        return Specification.super.or(other);
    }

    @Override
    public Predicate toPredicate(Root<GradeBook> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        return getFilterPredicate(root, query, criteriaBuilder);
    }

    private Predicate getFilterPredicate(Root<GradeBook> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        Join<GradeBook, AcademicTerm> gradeBookAcademicTermJoin = root.join("academicTerm", JoinType.LEFT);
        Join<GradeBook, Course> gradeBookCourseJoin = root.join("course", JoinType.LEFT);
        Join<GradeBook, Student> gradeBookStudentJoin = root.join("student", JoinType.LEFT);
        Join<GradeBook, FacultyMember> gradeBookFacultyMemberJoin = root.join("facultyMember", JoinType.LEFT);

        Predicate academicTerm;
        if (filterAcademicTerm != null)
            academicTerm = criteriaBuilder.equal(gradeBookAcademicTermJoin.get("id"), filterAcademicTerm);
        else academicTerm = criteriaBuilder.notEqual(gradeBookAcademicTermJoin.get("id"), -1);

        Predicate course;
        if (filterCourse != null)
            course = criteriaBuilder.equal(gradeBookCourseJoin.get("id"), filterCourse);
        else course = criteriaBuilder.notEqual(gradeBookCourseJoin.get("id"), -1);

        Predicate student;
        if (filterStudent != null)
            student = criteriaBuilder.equal(gradeBookStudentJoin.get("id"), filterStudent);
        else student = criteriaBuilder.notEqual(gradeBookStudentJoin.get("id"), -1);

        Predicate facultyMember;
        if (filterFacultyMember != null)
            facultyMember = criteriaBuilder.equal(gradeBookFacultyMemberJoin.get("id"), filterFacultyMember);
        else facultyMember = criteriaBuilder.notEqual(gradeBookFacultyMemberJoin.get("id"), -1);


        return criteriaBuilder.and(academicTerm, course, student, facultyMember);
    }

}
