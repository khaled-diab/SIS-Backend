package com.sis.dao.specification;

import com.sis.entities.*;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;

public class FacultyMemberEnrollmentSpecification implements Specification<FacultyMemberEnrollment> {

    private String searchValue;

    private Long filterCollege;

    private Long filterDepartment;

    private Long filterAcademicYear;

    private Long filterAcademicTerm;

    private Long filterFacultyMember;

    public FacultyMemberEnrollmentSpecification(String searchValue, Long filterCollege,
                                                Long filterDepartment, Long filterAcademicYear,
                                                Long filterAcademicTerm, Long filterFacultyMember) {
        this.searchValue = searchValue;
        this.filterCollege = filterCollege;
        this.filterDepartment = filterDepartment;
        this.filterAcademicYear = filterAcademicYear;
        this.filterAcademicTerm = filterAcademicTerm;
        this.filterFacultyMember = filterFacultyMember;
    }

    public FacultyMemberEnrollmentSpecification() {
        this.searchValue = null;
        this.filterCollege = null;
        this.filterDepartment = null;
        this.filterAcademicYear = null;
        this.filterAcademicTerm = null;
        this.filterFacultyMember = null;
    }

    @Override
    public Specification<FacultyMemberEnrollment> and(Specification<FacultyMemberEnrollment> other) {
        return Specification.super.and(other);
    }

    @Override
    public Specification<FacultyMemberEnrollment> or(Specification<FacultyMemberEnrollment> other) {
        return Specification.super.or(other);
    }

    @Override
    public Predicate toPredicate(Root<FacultyMemberEnrollment> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        Join<FacultyMemberEnrollment, Course> studentEnrollmentStudentJoin = root.join("course");
        Join<FacultyMemberEnrollment, FacultyMember> facultyMemberEnrollmentFacultyMemberJoin = root.join("facultyMember");
        if (searchValue != null) {
            Predicate searchPredicate = criteriaBuilder.or(
                    criteriaBuilder.like(studentEnrollmentStudentJoin.get("code"), "%" + searchValue + "%"),
                    criteriaBuilder.like(studentEnrollmentStudentJoin.get("nameAr"), "%" + searchValue + "%"),
                    criteriaBuilder.like(studentEnrollmentStudentJoin.get("nameEn"), "%" + searchValue + "%"),
                    criteriaBuilder.like(facultyMemberEnrollmentFacultyMemberJoin.get("nameAr"), "%" + searchValue + "%"),
                    criteriaBuilder.like(facultyMemberEnrollmentFacultyMemberJoin.get("nameEn"), "%" + searchValue + "%")
            );
            if (filterCollege == null && filterDepartment == null && filterAcademicYear == null
                    && filterAcademicTerm == null && filterFacultyMember == null) {
                return searchPredicate;
            }
            return criteriaBuilder.and(searchPredicate, getFilterPredicate(root, query, criteriaBuilder));
        }
        return getFilterPredicate(root, query, criteriaBuilder);
    }

    private Predicate getFilterPredicate(Root<FacultyMemberEnrollment> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        Join<FacultyMemberEnrollment, College> facultyMemberEnrollmentCollegeJoin = root.join("college");
        Join<FacultyMemberEnrollment, Department> facultyMemberEnrollmentDepartmentJoin = root.join("department");
        Join<FacultyMemberEnrollment, AcademicYear> facultyMemberEnrollmentAcademicYearJoin = root.join("academicYear");
        Join<FacultyMemberEnrollment, AcademicTerm> facultyMemberEnrollmentAcademicTermJoin = root.join("academicTerm");
        Join<FacultyMemberEnrollment, FacultyMember> facultyMemberEnrollmentFacultyMemberJoin = root.join("facultyMember");

        Predicate college;
        if (filterCollege != null)
            college = criteriaBuilder.equal(facultyMemberEnrollmentCollegeJoin.get("id"), filterCollege);
        else college = criteriaBuilder.notEqual(facultyMemberEnrollmentCollegeJoin.get("id"), -1);

        Predicate department;
        if (filterDepartment != null)
            department = criteriaBuilder.equal(facultyMemberEnrollmentDepartmentJoin.get("id"), filterDepartment);
        else department = criteriaBuilder.notEqual(facultyMemberEnrollmentDepartmentJoin.get("id"), -1);

        Predicate academicYear;
        if (filterAcademicYear != null)
            academicYear = criteriaBuilder.equal(facultyMemberEnrollmentAcademicYearJoin.get("id"), filterAcademicYear);
        else academicYear = criteriaBuilder.notEqual(facultyMemberEnrollmentAcademicYearJoin.get("id"), -1);

        Predicate academicTerm;
        if (filterAcademicTerm != null)
            academicTerm = criteriaBuilder.equal(facultyMemberEnrollmentAcademicTermJoin.get("id"), filterAcademicTerm);
        else academicTerm = criteriaBuilder.notEqual(facultyMemberEnrollmentAcademicTermJoin.get("id"), -1);

        Predicate facultyMember;
        if (filterFacultyMember != null)
            facultyMember = criteriaBuilder.equal(facultyMemberEnrollmentFacultyMemberJoin.get("id"), filterFacultyMember);
        else facultyMember = criteriaBuilder.notEqual(facultyMemberEnrollmentFacultyMemberJoin.get("id"), -1);

        return criteriaBuilder.and(college, department, academicYear, academicTerm, facultyMember);
    }

}
