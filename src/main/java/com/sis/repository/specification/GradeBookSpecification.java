package com.sis.repository.specification;

import com.sis.entity.*;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;

public class GradeBookSpecification implements Specification<GradeBook> {

    private final Long filterAcademicTerm;

    private final Long filterSection;

    private final Long filterStudent;

    public GradeBookSpecification(Long filterAcademicTerm, Long filterSection, Long filterStudent) {
        this.filterAcademicTerm = filterAcademicTerm;
        this.filterSection = filterSection;
        this.filterStudent = filterStudent;
    }

    public GradeBookSpecification() {
        this.filterAcademicTerm = null;
        this.filterSection = null;
        this.filterStudent = null;
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
        Join<GradeBook, Section> gradeBookSectionJoin = root.join("section", JoinType.LEFT);
        Join<GradeBook, Student> gradeBookStudentJoin = root.join("student", JoinType.LEFT);

        Predicate academicTerm;
        if (filterAcademicTerm != null)
            academicTerm = criteriaBuilder.equal(gradeBookAcademicTermJoin.get("id"), filterAcademicTerm);
        else academicTerm = criteriaBuilder.notEqual(gradeBookAcademicTermJoin.get("id"), -1);

        Predicate section;
        if (filterSection != null)
            section = criteriaBuilder.equal(gradeBookSectionJoin.get("id"), filterSection);
        else section = criteriaBuilder.notEqual(gradeBookSectionJoin.get("id"), -1);

        Predicate student;
        if (filterStudent != null)
            student = criteriaBuilder.equal(gradeBookStudentJoin.get("id"), filterStudent);
        else student = criteriaBuilder.notEqual(gradeBookStudentJoin.get("id"), -1);

        return criteriaBuilder.and(academicTerm, section, student);
    }

}
