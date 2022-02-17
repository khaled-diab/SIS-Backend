package com.sis.dao.specification;

import com.sis.entities.*;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;

public class TimetableSpecification implements Specification<Timetable> {

    private String searchValue;

    private Long filterCollege;

    private Long filterDepartment;

    private Long filterAcademicYear;

    private Long filterAcademicTerm;

    public TimetableSpecification(String searchValue, Long filterCollege,
                                  Long filterDepartment, Long filterAcademicYear,
                                  Long filterAcademicTerm) {
        this.searchValue = searchValue;
        this.filterCollege = filterCollege;
        this.filterDepartment = filterDepartment;
        this.filterAcademicYear = filterAcademicYear;
        this.filterAcademicTerm = filterAcademicTerm;
    }

    public TimetableSpecification() {
        this.searchValue = null;
        this.filterCollege = null;
        this.filterDepartment = null;
        this.filterAcademicYear = null;
        this.filterAcademicTerm = null;
    }

    @Override
    public Specification<Timetable> and(Specification<Timetable> other) {
        return Specification.super.and(other);
    }

    @Override
    public Specification<Timetable> or(Specification<Timetable> other) {
        return Specification.super.or(other);
    }

    @Override
    public Predicate toPredicate(Root<Timetable> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        Join<Timetable, College> timetableCollegeJoin = root.join("college");
        Join<Timetable, Department> timetableDepartmentJoin = root.join("department");
        if (searchValue != null) {
            Predicate searchPredicate = criteriaBuilder.or(
                    criteriaBuilder.like(timetableCollegeJoin.get("nameAr"), "%" + searchValue + "%"),
                    criteriaBuilder.like(timetableCollegeJoin.get("nameEn"), "%" + searchValue + "%"),
                    criteriaBuilder.like(timetableDepartmentJoin.get("nameAr"), "%" + searchValue + "%"),
                    criteriaBuilder.like(timetableDepartmentJoin.get("nameEn"), "%" + searchValue + "%")
            );
            if (filterCollege == null && filterDepartment == null && filterAcademicYear == null
                    && filterAcademicTerm == null) {
                return searchPredicate;
            }
            return criteriaBuilder.and(searchPredicate, getFilterPredicate(root, query, criteriaBuilder));
        }
        return getFilterPredicate(root, query, criteriaBuilder);
    }

    private Predicate getFilterPredicate(Root<Timetable> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        Join<Timetable, College> timetableCollegeJoin = root.join("college");
        Join<Timetable, Department> timetableDepartmentJoin = root.join("department");
        Join<Timetable, AcademicYear> timetableAcademicYearJoin = root.join("academicYear");
        Join<Timetable, AcademicTerm> timetableAcademicTermJoin = root.join("academicTerm");

        Predicate college;
        if (filterCollege != null)
            college = criteriaBuilder.equal(timetableCollegeJoin.get("id"), filterCollege);
        else college = criteriaBuilder.notEqual(timetableCollegeJoin.get("id"), -1);

        Predicate department;
        if (filterDepartment != null)
            department = criteriaBuilder.equal(timetableDepartmentJoin.get("id"), filterDepartment);
        else department = criteriaBuilder.notEqual(timetableDepartmentJoin.get("id"), -1);

        Predicate academicYear;
        if (filterAcademicYear != null)
            academicYear = criteriaBuilder.equal(timetableAcademicYearJoin.get("id"), filterAcademicYear);
        else academicYear = criteriaBuilder.notEqual(timetableAcademicYearJoin.get("id"), -1);

        Predicate academicTerm;
        if (filterAcademicTerm != null)
            academicTerm = criteriaBuilder.equal(timetableAcademicTermJoin.get("id"), filterAcademicTerm);
        else academicTerm = criteriaBuilder.notEqual(timetableAcademicTermJoin.get("id"), -1);

        return criteriaBuilder.and(college, department, academicYear, academicTerm);
    }

}
