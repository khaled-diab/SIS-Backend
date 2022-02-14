package com.sis.dao.specification;

import com.sis.entities.*;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;

public class StudentEnrollmentSpecification implements Specification<StudentEnrollment> {

    private String searchValue;

    private Long filterCollege;

    private Long filterDepartment;

    private Long filterAcademicYear;

    private Long filterAcademicTerm;

    private Long filterCourse;

    private Long filterStudyType;

    private Long filterSection;

    private Long filterMajor;

    public StudentEnrollmentSpecification(String searchValue, Long filterCollege,
                                          Long filterDepartment, Long filterAcademicYear,
                                          Long filterAcademicTerm, Long filterCourse, Long filterStudyType,
                                          Long filterSection, Long filterMajor) {
        this.searchValue = searchValue;
        this.filterCollege = filterCollege;
        this.filterDepartment = filterDepartment;
        this.filterAcademicYear = filterAcademicYear;
        this.filterAcademicTerm = filterAcademicTerm;
        this.filterCourse = filterCourse;
        this.filterStudyType = filterStudyType;
        this.filterSection = filterSection;
        this.filterMajor = filterMajor;
    }

    public StudentEnrollmentSpecification() {
        this.searchValue = null;
        this.filterCollege = null;
        this.filterDepartment = null;
        this.filterAcademicYear = null;
        this.filterAcademicTerm = null;
        this.filterCourse = null;
        this.filterStudyType = null;
        this.filterSection = null;
        this.filterMajor = null;
    }

    @Override
    public Specification<StudentEnrollment> and(Specification<StudentEnrollment> other) {
        return Specification.super.and(other);
    }

    @Override
    public Specification<StudentEnrollment> or(Specification<StudentEnrollment> other) {
        return Specification.super.or(other);
    }

    @Override
    public Predicate toPredicate(Root<StudentEnrollment> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        Join<StudentEnrollment, Student> studentEnrollmentStudentJoin = root.join("student");
        if (searchValue != null) {
            Predicate searchPredicate = criteriaBuilder.or(
                    criteriaBuilder.like(studentEnrollmentStudentJoin.get("nameAr"), "%" + searchValue + "%"),
                    criteriaBuilder.like(studentEnrollmentStudentJoin.get("nameEn"), "%" + searchValue + "%"),
                    criteriaBuilder.like(studentEnrollmentStudentJoin.get("universityId"), "%" + searchValue + "%")
            );
            if (filterCollege == null && filterDepartment == null && filterAcademicYear == null
                    && filterAcademicTerm == null && filterCourse == null && filterSection == null
                    && filterStudyType == null && filterMajor == null) {
                return searchPredicate;
            }
            return criteriaBuilder.and(searchPredicate, getFilterPredicate(root, query, criteriaBuilder));
        }
        return getFilterPredicate(root, query, criteriaBuilder);
    }

    private Predicate getFilterPredicate(Root<StudentEnrollment> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        Join<StudentEnrollment, College> sectionCollegeJoin = root.join("college");
        Join<StudentEnrollment, Department> sectionDepartmentJoin = root.join("department");
        Join<StudentEnrollment, AcademicYear> sectionAcademicYearJoin = root.join("academicYear");
        Join<StudentEnrollment, AcademicTerm> sectionAcademicTermJoin = root.join("academicTerm");
        Join<StudentEnrollment, Course> sectionCourseJoin = root.join("course");
        Join<StudentEnrollment, Section> sectionSectionJoin = root.join("sectionNumber");
        Join<StudentEnrollment, StudyType> sectionStudyTypeJoin = root.join("studyType");
        Join<StudentEnrollment, Major> studentEnrollmentMajorJoin = root.join("major");

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

        Predicate section;
        if (filterSection != null)
            section = criteriaBuilder.equal(sectionSectionJoin.get("id"), filterSection);
        else section = criteriaBuilder.notEqual(sectionSectionJoin.get("id"), -1);

        Predicate studyType;
        if (filterStudyType != null)
            studyType = criteriaBuilder.equal(sectionStudyTypeJoin.get("id"), filterStudyType);
        else studyType = criteriaBuilder.notEqual(sectionStudyTypeJoin.get("id"), -1);

        Predicate major;
        if (filterMajor != null)
            major = criteriaBuilder.equal(studentEnrollmentMajorJoin.get("id"), filterMajor);
        else major = criteriaBuilder.notEqual(studentEnrollmentMajorJoin.get("id"), -1);

        return criteriaBuilder.and(college, department, academicYear,
                academicTerm, course, studyType, section, major);
    }

}
