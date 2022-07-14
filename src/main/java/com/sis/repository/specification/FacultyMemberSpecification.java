package com.sis.repository.specification;

import com.sis.entity.College;
import com.sis.entity.Department;
import com.sis.entity.FacultyMember;
import com.sis.entity.security.User;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;

public class FacultyMemberSpecification implements Specification<FacultyMember> {

    private final String searchValue;

    private final Long filterCollege;

    private final Long filterDepartment;

    public FacultyMemberSpecification(String searchValue, Long filterCollege, Long filterDepartment) {
        this.searchValue = searchValue;
        this.filterCollege = filterCollege;
        this.filterDepartment = filterDepartment;
    }

    public FacultyMemberSpecification() {
        this.searchValue = null;
        this.filterCollege = null;
        this.filterDepartment = null;
    }

    @Override
    public Specification<FacultyMember> and(Specification<FacultyMember> other) {
        return Specification.super.and(other);
    }

    @Override
    public Specification<FacultyMember> or(Specification<FacultyMember> other) {
        return Specification.super.or(other);
    }

    @Override
    public Predicate toPredicate(Root<FacultyMember> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        if (searchValue != null) {
            Predicate x = criteriaBuilder.or(
                    criteriaBuilder.like(root.get("nameAr"), "%" + searchValue + "%"),
                    criteriaBuilder.like(root.get("nameEn"), "%" + searchValue + "%"),
                    criteriaBuilder.like(root.get("universityMail"), "%" + searchValue + "%")
            );
            if (filterCollege == null && filterDepartment == null) {
                return x;
            }
            return criteriaBuilder.and(x, getFilterPredicate(root, query, criteriaBuilder));
        }
        return getFilterPredicate(root, query, criteriaBuilder);
    }

    private Predicate getFilterPredicate(Root<FacultyMember> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        Join<FacultyMember, College> facultyMemberCollegeJoin = root.join("college", JoinType.LEFT);
        Join<FacultyMember, Department> facultyMemberDepartmentJoin = root.join("department", JoinType.LEFT);
        Join<FacultyMember, User> facultyMemberUserJoin = root.join("user", JoinType.LEFT);

        Predicate college;
        if (filterCollege != null)
            college = criteriaBuilder.equal(facultyMemberCollegeJoin.get("id"), filterCollege);
        else college = criteriaBuilder.notEqual(facultyMemberCollegeJoin.get("id"), -1);

        Predicate department;
        if (filterDepartment != null)
            department = criteriaBuilder.equal(facultyMemberDepartmentJoin.get("id"), filterDepartment);
        else department = criteriaBuilder.notEqual(facultyMemberDepartmentJoin.get("id"), -1);

        Predicate user = criteriaBuilder.isNotNull(facultyMemberUserJoin.get("id"));

        return criteriaBuilder.and(college, department, user);
    }

}
