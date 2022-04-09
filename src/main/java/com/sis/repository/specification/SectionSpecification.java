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
            Predicate searchPredicate = criteriaBuilder.or(
                    criteriaBuilder.like(sectionCourseJoin.get("code"), "%" + searchValue + "%"),
                    criteriaBuilder.like(sectionCourseJoin.get("nameAr"), "%" + searchValue + "%"),
                    criteriaBuilder.like(sectionCourseJoin.get("nameEn"), "%" + searchValue + "%"),
                    criteriaBuilder.like(root.get("sectionNumber"), "%" + searchValue + "%")
            );
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
        Join<Section, College> sectionCollegeJoin = root.join("college");
        Join<Section, Department> sectionDepartmentJoin = root.join("department");
        Join<Section, AcademicYear> sectionAcademicYearJoin = root.join("academicYear");
        Join<Section, AcademicTerm> sectionAcademicTermJoin = root.join("academicTerm");
        Join<Section, Course> sectionCourseJoin = root.join("course");
        Join<Section, StudyType> sectionStudyTypeJoin = root.join("studyType");
        Join<Section, Major> sectionMajorJoin = root.join("major");

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
        else major = criteriaBuilder.notEqual(sectionMajorJoin.get("id"), -1);

        return criteriaBuilder.and(college, department,
                academicYear, academicTerm, course, studyType, major);
    }

}
