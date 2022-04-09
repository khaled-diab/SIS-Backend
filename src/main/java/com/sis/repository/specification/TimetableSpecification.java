package com.sis.repository.specification;

import com.sis.entity.*;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;

public class TimetableSpecification implements Specification<Timetable> {

    private final Long filterCollege;

    private final Long filterDepartment;

    private final Long filterAcademicYear;

    private final Long filterAcademicTerm;

    private final Long filterFacultyMember;

    private final Long filterCourse;

    private final Long filterSection;

    private final String filterDay;

    public TimetableSpecification(Long filterCollege, Long filterDepartment,
                                  Long filterAcademicYear, Long filterAcademicTerm, Long filterFacultyMember,
                                  Long filterCourse, Long filterSection, String filterDay) {
        this.filterCollege = filterCollege;
        this.filterDepartment = filterDepartment;
        this.filterAcademicYear = filterAcademicYear;
        this.filterAcademicTerm = filterAcademicTerm;
        this.filterFacultyMember = filterFacultyMember;
        this.filterCourse = filterCourse;
        this.filterSection = filterSection;
        this.filterDay = filterDay;
    }

    public TimetableSpecification() {
        this.filterCollege = null;
        this.filterDepartment = null;
        this.filterAcademicYear = null;
        this.filterAcademicTerm = null;
        this.filterFacultyMember = null;
        this.filterCourse = null;
        this.filterSection = null;
        this.filterDay = null;
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
        return getFilterPredicate(root, query, criteriaBuilder);
    }

    private Predicate getFilterPredicate(Root<Timetable> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        Join<Timetable, College> timetableCollegeJoin = root.join("college");
        Join<Timetable, Department> timetableDepartmentJoin = root.join("department");
        Join<Timetable, AcademicYear> timetableAcademicYearJoin = root.join("academicYear");
        Join<Timetable, AcademicTerm> timetableAcademicTermJoin = root.join("academicTerm");
        Join<Timetable, FacultyMember> timetableFacultyMemberJoin = root.join("facultyMember");
        Join<Timetable, Course> timetableCourseJoin = root.join("course");
        Join<Timetable, Section> timetableSectionJoin = root.join("section");

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

        Predicate facultyMember;
        if (filterFacultyMember != null)
            facultyMember = criteriaBuilder.equal(timetableFacultyMemberJoin.get("id"), filterFacultyMember);
        else facultyMember = criteriaBuilder.notEqual(timetableFacultyMemberJoin.get("id"), -1);

        Predicate course;
        if (filterCourse != null)
            course = criteriaBuilder.equal(timetableCourseJoin.get("id"), filterCourse);
        else course = criteriaBuilder.notEqual(timetableCourseJoin.get("id"), -1);

        Predicate section;
        if (filterSection != null)
            section = criteriaBuilder.equal(timetableSectionJoin.get("id"), filterSection);
        else section = criteriaBuilder.notEqual(timetableSectionJoin.get("id"), -1);

        Predicate day;
        if (filterDay!= null && !filterDay.trim().isEmpty())
            day = criteriaBuilder.equal(root.get("day"), filterDay);
        else day = criteriaBuilder.notEqual(root.get("day"), "");

        return criteriaBuilder.and(college, department, academicYear, academicTerm,
                facultyMember, course, section, day);
    }

}
