package com.sis.repository.specification;

import com.sis.entity.College;
import com.sis.entity.Department;
import com.sis.entity.Major;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;

public class MajorSpecification implements Specification<Major> {

    private final String searchValue;

    private final Long filterCollege;

    private final Long filterDepartment;

    public MajorSpecification(String searchValue, Long filterCollege, Long filterDepartment) {
        this.searchValue = searchValue;
        this.filterCollege = filterCollege;
        this.filterDepartment = filterDepartment;
    }

    public MajorSpecification() {
        this.searchValue = null;
        this.filterCollege = null;
        this.filterDepartment = null;
    }

    @Override
    public Specification<Major> and(Specification<Major> other) {
        return Specification.super.and(other);
    }

    @Override
    public Specification<Major> or(Specification<Major> other) {
        return Specification.super.or(other);
    }

    @Override
    public Predicate toPredicate(Root<Major> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        if (searchValue != null) {
            Predicate x = criteriaBuilder.or(
                    criteriaBuilder.like(root.get("nameAr"), "%" + searchValue + "%"),
                    criteriaBuilder.like(root.get("nameEn"), "%" + searchValue + "%")
                    );
            if (filterCollege == null && filterDepartment == null) {
                return x;
            }
            return criteriaBuilder.and(x, getFilterPredicate(root, query, criteriaBuilder));
        }
        return getFilterPredicate(root, query, criteriaBuilder);
    }

    private Predicate getFilterPredicate(Root<Major> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        Join<Major, College> majorCollegeJoin = root.join("college", JoinType.LEFT);
        Join<Major, Department> majorDepartmentJoin = root.join("department", JoinType.LEFT);

        Predicate college;
        if (filterCollege != null)
            college = criteriaBuilder.equal(majorCollegeJoin.get("id"), filterCollege);
        else college = criteriaBuilder.notEqual(majorCollegeJoin.get("id"), -1);

        Predicate department;
        if (filterDepartment != null)
            department = criteriaBuilder.equal(majorDepartmentJoin.get("id"), filterDepartment);
        else department = criteriaBuilder.notEqual(majorDepartmentJoin.get("id"), -1);

        return criteriaBuilder.and(college, department);
    }

}
