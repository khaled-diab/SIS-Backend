package com.sis.repository.specification;

import com.sis.entity.*;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;

public class SectionSpecification implements Specification<Section> {

    private final String searchValue;

    private final Long filterCollege;

    private final Long filterDepartment;

    private final Long filterAcademicYear;

    private final Long filterAcademicTerm;

    private final Long filterCourse;

    private final Long filterStudyType;

    private final Long filterMajor;

    public SectionSpecification(String searchValue, Long filterCollege, Long filterDepartment,
                                Long filterAcademicYear, Long filterAcademicTerm, Long filterCourse,
                                Long filterStudyType, Long filterMajor) {
        this.searchValue = searchValue;
        this.filterCollege = filterCollege;
        this.filterDepartment = filterDepartment;
        this.filterAcademicYear = filterAcademicYear;
        this.filterAcademicTerm = filterAcademicTerm;
        this.filterCourse = filterCourse;
        this.filterStudyType = filterStudyType;
        this.filterMajor = filterMajor;
    }

    public SectionSpecification() {
        this.searchValue = null;
        this.filterCollege = null;
        this.filterDepartment = null;
        this.filterAcademicYear = null;
        this.filterAcademicTerm = null;
        this.filterCourse = null;
        this.filterStudyType = null;
        this.filterMajor = null;
    }

    @Override
    public Specification<Section> and(Specification<Section> other) {
        return Specification.super.and(other);
    }

    @Override
    public Specification<Section> or(Specification<Section> other) {
        return Specification.super.or(other);
    }

    @Override
    public Predicate toPredicate(Root<Section> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        Join<Section, Course> sectionCourseJoin = root.join("course");
        if (searchValue != null) {
            Predicate x = criteriaBuilder.or(
                    criteriaBuilder.like(sectionCourseJoin.get("code"), "%" + searchValue + "%"),
                    criteriaBuilder.like(sectionCourseJoin.get("nameAr"), "%" + searchValue + "%"),
                    criteriaBuilder.like(sectionCourseJoin.get("nameEn"), "%" + searchValue + "%"),
                    criteriaBuilder.like(root.get("sectionNumber"), "%" + searchValue + "%")
            );
            int capacity;
            try {
                capacity = Integer.parseInt(searchValue);
            } catch (Exception ex) {
                capacity = -1;
            }
            Predicate y = criteriaBuilder.or(
                    criteriaBuilder.equal(root.get("capacity"), capacity)
            );
            Predicate searchPredicate = criteriaBuilder.or(x, y);
            if (filterCollege == null && filterDepartment == null && filterAcademicYear == null
                    && filterAcademicTerm == null && filterCourse == null
                    && filterStudyType == null && filterMajor == null) {
                return searchPredicate;
            }
            return criteriaBuilder.and(searchPredicate, getFilterPredicate(root, query, criteriaBuilder));
        }
        return getFilterPredicate(root, query, criteriaBuilder);
    }

    private Predicate getFilterPredicate(Root<Section> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        Join<Section, College> sectionCollegeJoin = root.join("college", JoinType.LEFT);
        Join<Section, Department> sectionDepartmentJoin = root.join("department", JoinType.LEFT);
        Join<Section, AcademicYear> sectionAcademicYearJoin = root.join("academicYear", JoinType.LEFT);
        Join<Section, AcademicTerm> sectionAcademicTermJoin = root.join("academicTerm", JoinType.LEFT);
        Join<Section, Course> sectionCourseJoin = root.join("course", JoinType.LEFT);
        Join<Section, StudyType> sectionStudyTypeJoin = root.join("studyType", JoinType.LEFT);
        Join<Section, Major> sectionMajorJoin = root.join("major", JoinType.LEFT);

        Predicate college;
        if (filterCollege != null)
            college = criteriaBuilder.equal(sectionCollegeJoin.get("id"), filterCollege);
        else college = criteriaBuilder.notEqual(sectionCollegeJoin.get("id"), -1);

        Predicate department;
        if (filterDepartment != null)
            department = criteriaBuilder.equal(sectionDepartmentJoin.get("id"), filterDepartment);
        else department = criteriaBuilder.notEqual(sectionDepartmentJoin.get("id"), -1);

        Predicate academicYear;
        if (filterAcademicYear != null)
            academicYear = criteriaBuilder.equal(sectionAcademicYearJoin.get("id"), filterAcademicYear);
        else academicYear = criteriaBuilder.notEqual(sectionAcademicYearJoin.get("id"), -1);

        Predicate academicTerm;
        if (filterAcademicTerm != null)
            academicTerm = criteriaBuilder.equal(sectionAcademicTermJoin.get("id"), filterAcademicTerm);
        else academicTerm = criteriaBuilder.notEqual(sectionAcademicTermJoin.get("id"), -1);

        Predicate course;
        if (filterCourse != null)
            course = criteriaBuilder.equal(sectionCourseJoin.get("id"), filterCourse);
        else course = criteriaBuilder.notEqual(sectionCourseJoin.get("id"), -1);

        Predicate studyType;
        if (filterStudyType != null)
            studyType = criteriaBuilder.equal(sectionStudyTypeJoin.get("id"), filterStudyType);
        else studyType = criteriaBuilder.notEqual(sectionStudyTypeJoin.get("id"), -1);

        Predicate major;
        if (filterMajor != null)
            major = criteriaBuilder.equal(sectionMajorJoin.get("id"), filterMajor);
        else major = criteriaBuilder.or(criteriaBuilder.notEqual(sectionMajorJoin.get("id"), -1),criteriaBuilder.isNull(sectionMajorJoin.get("id")));

        return criteriaBuilder.and(college, department,
                academicYear, academicTerm, course, studyType, major);
    }

}
