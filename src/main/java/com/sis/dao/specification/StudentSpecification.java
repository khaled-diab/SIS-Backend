package com.sis.dao.specification;

import com.sis.entities.College;
import com.sis.entities.Department;
import com.sis.entities.Student;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;

public class StudentSpecification implements Specification<Student> {

    private String searchValue;

    private Long filterCollege;

    private Long filterDepartment;

    private String filterLevel;

    public StudentSpecification(String searchValue, Long filterCollege, Long filterDepartment, String filterLevel) {
        this.searchValue = searchValue;
        this.filterCollege = filterCollege;
        this.filterDepartment = filterDepartment;
        this.filterLevel = filterLevel;
    }

    public StudentSpecification() {
        this.searchValue = null;
        this.filterCollege = null;
        this.filterDepartment = null;
        this.filterLevel = null;
    }

    @Override
    public Specification<Student> and(Specification<Student> other) {
        return Specification.super.and(other);
    }

    @Override
    public Specification<Student> or(Specification<Student> other) {
        return Specification.super.or(other);
    }

    @Override
    public Predicate toPredicate(Root<Student> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        if (searchValue != null) {
            Predicate searchPredicate = criteriaBuilder.or(
                    criteriaBuilder.like(root.get("nameAr"), "%" + searchValue + "%"),
                    criteriaBuilder.like(root.get("nameEn"), "%" + searchValue + "%"),
                    criteriaBuilder.like(root.get("year"), "%" + searchValue + "%")
            );
            try {
                searchPredicate = criteriaBuilder.or(searchPredicate,
                        criteriaBuilder.equal(root.get("universityId"), Long.parseLong(searchValue))
                );
            } catch (Exception e) {
            }
            if (filterCollege == null && filterDepartment == null && filterLevel == null) {
                return searchPredicate;
            }
            return criteriaBuilder.and(searchPredicate, getFilterPredicate(root, query, criteriaBuilder));
        }
        return getFilterPredicate(root, query, criteriaBuilder);
    }

    private Predicate getFilterPredicate(Root<Student> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        Join<Student, College> studentCollegeJoin = root.join("collegeId");
        Join<Student, Department> studentDepartmentJoin = root.join("departmentId");

        Predicate college;
        if (filterCollege != null)
            college = criteriaBuilder.equal(studentCollegeJoin.get("id"), filterCollege);
        else college = criteriaBuilder.notEqual(studentCollegeJoin.get("id"), -1);

        Predicate department;
        if (filterDepartment != null)
            department = criteriaBuilder.equal(studentDepartmentJoin.get("id"), filterDepartment);
        else department = criteriaBuilder.notEqual(studentDepartmentJoin.get("id"), -1);

        Predicate level;
        if (filterLevel != null && !filterLevel.trim().isEmpty())
            level = criteriaBuilder.equal(root.get("level"), filterLevel);
        else level = criteriaBuilder.notEqual(root.get("level"), "");

        return criteriaBuilder.and(college, department, level);
    }

}
