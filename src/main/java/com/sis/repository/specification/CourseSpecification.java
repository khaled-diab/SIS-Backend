package com.sis.repository.specification;

import com.sis.entity.College;
import com.sis.entity.Course;
import com.sis.entity.Department;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;

public class CourseSpecification implements Specification<Course> {

    private final String searchValue;

    private final Long filterCollege;
    private final Long filterDepartment;

    public CourseSpecification(String searchValue, Long filterCollege, Long filterDepartment) {
        this.searchValue = searchValue;
        this.filterCollege = filterCollege;
        this.filterDepartment = filterDepartment;
    }

    public CourseSpecification() {
        this.searchValue = null;
        this.filterCollege = null;
        this.filterDepartment = null;
    }

    @Override
    public Specification<Course> and(Specification<Course> other) {
        return Specification.super.and(other);
    }

    @Override
    public Specification<Course> or(Specification<Course> other) {
        return Specification.super.or(other);
    }

    @Override
    public Predicate toPredicate(Root<Course> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        if (searchValue != null) {
            Predicate x = criteriaBuilder.or(
                    criteriaBuilder.like(root.get("nameAr"), "%" + searchValue + "%"),
                    criteriaBuilder.like(root.get("nameEn"), "%" + searchValue + "%"),
                    criteriaBuilder.like(root.get("code"), "%" + searchValue + "%")

            );
            float f1;

            try {
                f1 = Float.parseFloat(searchValue);
            } catch (Exception ex) {
                f1 = -1;
            }
            Predicate y = criteriaBuilder.or(
                    criteriaBuilder.equal(root.get("totalHours"), f1),
                    criteriaBuilder.equal(root.get("finalGrade"), f1)
            );
            if (filterCollege == null && filterDepartment == null) {
                return criteriaBuilder.or(x, y);
            }
            return criteriaBuilder.and(criteriaBuilder.or(x, y),
                    getFilterPredicate(root, query, criteriaBuilder));
        }

        return getFilterPredicate(root, query, criteriaBuilder);

    }

    private Predicate getFilterPredicate(Root<Course> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        Join<Course, College> courseCollegeJoin = root.join("college");
        Join<Course, Department> courseDepartmentJoin = root.join("department");
        System.out.println(filterCollege);

        Predicate college = criteriaBuilder.equal(courseCollegeJoin.get("id"), filterCollege);

        Predicate department = criteriaBuilder.equal(courseDepartmentJoin.get("id"), filterDepartment);

        if (filterDepartment == null)
            return college;
        return criteriaBuilder.and(college, department);
    }
}



