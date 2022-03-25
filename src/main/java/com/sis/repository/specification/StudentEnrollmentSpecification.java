package com.sis.repository.specification;

import com.sis.entities.*;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;

public class StudentEnrollmentSpecification implements Specification<StudentEnrollment> {

    private final String searchValue;

    private final Long filterCollege;

    private final Long filterDepartment;

    private final Long filterAcademicYear;

    private final Long filterAcademicTerm;

    private final Long filterCourse;

    private final Long filterStudent;

    private final Long filterStudyType;

    private final Long filterSection;

    private final Long filterMajor;

    public StudentEnrollmentSpecification(String searchValue, Long filterCollege, Long filterDepartment,
                                          Long filterAcademicYear, Long filterAcademicTerm, Long filterCourse,
                                          Long filterStudent, Long filterStudyType, Long filterSection,
                                          Long filterMajor) {
        this.searchValue = searchValue;
        this.filterCollege = filterCollege;
        this.filterDepartment = filterDepartment;
        this.filterAcademicYear = filterAcademicYear;
        this.filterAcademicTerm = filterAcademicTerm;
        this.filterCourse = filterCourse;
        this.filterStudent = filterStudent;
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
        this.filterStudent = null;
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
                    criteriaBuilder.like(studentEnrollmentStudentJoin.get("nameEn"), "%" + searchValue + "%")
            );
            try {
                searchPredicate = criteriaBuilder.or(searchPredicate,
                        criteriaBuilder.equal(studentEnrollmentStudentJoin.get("universityId"), Long.parseLong(searchValue))
                );
            } catch (NumberFormatException e) {
            }
            if (filterCollege == null && filterDepartment == null && filterAcademicYear == null
                    && filterAcademicTerm == null && filterCourse == null && filterStudent == null && filterSection == null
                    && filterStudyType == null && filterMajor == null) {
                return searchPredicate;
            }
            return criteriaBuilder.and(searchPredicate, getFilterPredicate(root, query, criteriaBuilder));
        }
        return getFilterPredicate(root, query, criteriaBuilder);
    }

    private Predicate getFilterPredicate(Root<StudentEnrollment> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        Join<StudentEnrollment, College> studentEnrollmentCollegeJoin = root.join("college");
        Join<StudentEnrollment, Department> studentEnrollmentDepartmentJoin = root.join("department");
        Join<StudentEnrollment, AcademicYear> studentEnrollmentAcademicYearJoin = root.join("academicYear");
        Join<StudentEnrollment, AcademicTerm> studentEnrollmentAcademicTermJoin = root.join("academicTerm");
        Join<StudentEnrollment, Course> studentEnrollmentCourseJoin = root.join("course");
        Join<StudentEnrollment, Student> studentEnrollmentStudentJoin = root.join("student");
        Join<StudentEnrollment, Section> studentEnrollmentSectionJoin = root.join("section");
        Join<StudentEnrollment, StudyType> studentEnrollmentStudyTypeJoin = root.join("studyType");
        Join<StudentEnrollment, Major> studentEnrollmentMajorJoin = root.join("major");

        Predicate college;
        if (filterCollege != null)
            college = criteriaBuilder.equal(studentEnrollmentCollegeJoin.get("id"), filterCollege);
        else college = criteriaBuilder.notEqual(studentEnrollmentCollegeJoin.get("id"), -1);

        Predicate department;
        if (filterDepartment != null)
            department = criteriaBuilder.equal(studentEnrollmentDepartmentJoin.get("id"), filterDepartment);
        else department = criteriaBuilder.notEqual(studentEnrollmentDepartmentJoin.get("id"), -1);

        Predicate academicYear;
        if (filterAcademicYear != null)
            academicYear = criteriaBuilder.equal(studentEnrollmentAcademicYearJoin.get("id"), filterAcademicYear);
        else academicYear = criteriaBuilder.notEqual(studentEnrollmentAcademicYearJoin.get("id"), -1);

        Predicate academicTerm;
        if (filterAcademicTerm != null)
            academicTerm = criteriaBuilder.equal(studentEnrollmentAcademicTermJoin.get("id"), filterAcademicTerm);
        else academicTerm = criteriaBuilder.notEqual(studentEnrollmentAcademicTermJoin.get("id"), -1);

        Predicate course;
        if (filterCourse != null)
            course = criteriaBuilder.equal(studentEnrollmentCourseJoin.get("id"), filterCourse);
        else course = criteriaBuilder.notEqual(studentEnrollmentCourseJoin.get("id"), -1);

        Predicate student;
        if (filterStudent != null)
            student = criteriaBuilder.equal(studentEnrollmentStudentJoin.get("id"), filterStudent);
        else student = criteriaBuilder.notEqual(studentEnrollmentStudentJoin.get("id"), -1);

        Predicate section;
        if (filterSection != null)
            section = criteriaBuilder.equal(studentEnrollmentSectionJoin.get("id"), filterSection);
        else section = criteriaBuilder.notEqual(studentEnrollmentSectionJoin.get("id"), -1);

        Predicate studyType;
        if (filterStudyType != null)
            studyType = criteriaBuilder.equal(studentEnrollmentStudyTypeJoin.get("id"), filterStudyType);
        else studyType = criteriaBuilder.notEqual(studentEnrollmentStudyTypeJoin.get("id"), -1);

        Predicate major;
        if (filterMajor != null)
            major = criteriaBuilder.equal(studentEnrollmentMajorJoin.get("id"), filterMajor);
        else major = criteriaBuilder.notEqual(studentEnrollmentMajorJoin.get("id"), -1);

        return criteriaBuilder.and(college, department, academicYear,
                academicTerm, course, student, studyType, section, major);
    }

}
