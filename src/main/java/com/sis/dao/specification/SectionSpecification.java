package com.sis.dao.specification;

import com.sis.entities.*;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;

public class SectionSpecification implements Specification<Section> {

    private String searchValue;

    private Long filterCollege;

    private Long filterDepartment;

    private Long filterAcademicYear;

    private Long filterAcademicTerm;

    private Long filterCourse;

    private Long filterStudyType;

    private Long filterSectionNumber;

    public SectionSpecification(String searchValue, Long filterCollege, Long filterDepartment,
                                Long filterAcademicYear, Long filterAcademicTerm, Long filterCourse,
                                Long filterStudyType, Long filterSectionNumber) {
        this.searchValue = searchValue;
        this.filterCollege = filterCollege;
        this.filterDepartment = filterDepartment;
        this.filterAcademicYear = filterAcademicYear;
        this.filterAcademicTerm = filterAcademicTerm;
        this.filterCourse = filterCourse;
        this.filterStudyType = filterStudyType;
        this.filterSectionNumber = filterSectionNumber;
    }

    public SectionSpecification() {
        this.searchValue = null;
        this.filterCollege = null;
        this.filterDepartment = null;
        this.filterAcademicYear = null;
        this.filterAcademicTerm = null;
        this.filterCourse = null;
        this.filterStudyType = null;
        this.filterSectionNumber = null;
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
        if (searchValue != null) {
            Predicate searchPredicate = criteriaBuilder.or(
                    criteriaBuilder.like(root.get("course"), "%" + searchValue + "%"),
                    criteriaBuilder.like(root.get("sectionNumber"), "%" + searchValue + "%")
            );
            if (filterCollege == null && filterDepartment == null && filterAcademicYear == null
                    && filterAcademicTerm == null && filterCourse == null && filterSectionNumber == null
                    && filterStudyType == null) {
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
        Join<Section, Section> sectionSectionJoin = root.join("sectionNumber");
        Join<Section, StudyType> sectionStudyTypeJoin = root.join("studyType");

//        System.out.println(filterCollege);
//        System.out.println(filterDepartment);

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

        Predicate sectionNumber;
        if (filterSectionNumber != null)
            sectionNumber = criteriaBuilder.equal(sectionSectionJoin.get("id"), filterSectionNumber);
        else sectionNumber = criteriaBuilder.notEqual(sectionSectionJoin.get("id"), -1);

        Predicate studyType;
        if (filterStudyType != null)
            studyType = criteriaBuilder.equal(sectionStudyTypeJoin.get("id"), filterStudyType);
        else studyType = criteriaBuilder.notEqual(sectionStudyTypeJoin.get("id"), -1);


        return criteriaBuilder.and(college, department, academicYear, academicTerm, course, studyType, sectionNumber);
    }

}
