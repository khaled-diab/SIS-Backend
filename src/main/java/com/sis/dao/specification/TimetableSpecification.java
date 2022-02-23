package com.sis.dao.specification;

import com.sis.entities.*;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.sql.Time;

public class TimetableSpecification implements Specification<Timetable> {

    private String searchValue;

    private Long filterCollege;

    private Long filterDepartment;

    private Long filterAcademicYear;

    private Long filterAcademicTerm;

    private Long filterFacultyMember;

    private Long filterCourse;

    private Long filterSection;

    private String filterDay;

    public TimetableSpecification(String searchValue, Long filterCollege, Long filterDepartment,
                                  Long filterAcademicYear, Long filterAcademicTerm, Long filterFacultyMember,
                                  Long filterCourse, Long filterSection, String filterDay) {
        this.searchValue = searchValue;
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
        this.searchValue = null;
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
//        if (searchValue != null) {
//            Predicate searchPredicate = criteriaBuilder.or();
//            if (filterCollege == null && filterDepartment == null && filterAcademicYear == null
//                    && filterAcademicTerm == null && filterFacultyMember == null
//                    && filterCourse == null && filterSection == null && filterDay.isEmpty()) {
//                return searchPredicate;
//            }
//            return criteriaBuilder.and(searchPredicate, getFilterPredicate(root, query, criteriaBuilder));
//        }
        return getFilterPredicate(root, query, criteriaBuilder);
    }

    private Predicate getFilterPredicate(Root<Timetable> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        Join<Timetable, College> timetableCollegeJoin = root.join("college");
        Join<Timetable, Department> timetableDepartmentJoin = root.join("department");
        Join<Timetable, AcademicYear> timetableAcademicYearJoin = root.join("academicYear");
        Join<Timetable, AcademicTerm> timetableAcademicTermJoin = root.join("academicTerm");
        Join<Timetable, FacultyMember> timetableFacultyMemberJoin = root.join("facultyMember");
        Join<Timetable, Course> timetableCourseJoin = root.join("course");
//        Join<Timetable, Section> timetableSectionJoin = root.join("sections");
//        Path<Timetable> timetableTimetableJoin = root.get("id").get("day");

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

//        Predicate section;
//        if (filterSection != null)
//            section = criteriaBuilder.equal(timetableSectionJoin.get("id"), filterSection);
//        else section = criteriaBuilder.notEqual(timetableSectionJoin.get("id"), -1);
//
//        Predicate day;
//        if (filterDay!= null && !filterDay.trim().isEmpty())
//            day = criteriaBuilder.equal(root.get("day"), filterDay);
//        else day = criteriaBuilder.notEqual(root.get("day"), "");

        return criteriaBuilder.and(college, department, academicYear, academicTerm,
                facultyMember, course);
    }

}
