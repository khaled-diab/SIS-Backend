package com.sis.specification;

import com.sis.entities.College;
import com.sis.entities.Department;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;

import com.sis.entities.FacultyMember;

public class FacultyMemberSpecification implements Specification<FacultyMember> {

    private String searchValue;

    private Long filterCollege;

//    private Long filterDepartment;

//    public FacultyMemberSpecification(String searchValue, Long filterCollege, Long filterDepartment) {
    public FacultyMemberSpecification(String searchValue, Long filterCollege) {
        this.searchValue = searchValue;
        this.filterCollege = filterCollege;
//        this.filterDepartment = filterDepartment;
    }

    public FacultyMemberSpecification() {
        this.searchValue = null;
        this.filterCollege = null;
//        this.filterDepartment = null;
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
                    criteriaBuilder.like(root.get("degree"), "%" + searchValue + "%"),
                    criteriaBuilder.like(root.get("universityMail"), "%" + searchValue + "%")
            );
            if (filterCollege == null) {
                return x;
            }
            return criteriaBuilder.and(x, getFilterPredicate(root, query, criteriaBuilder));
        }
        return getFilterPredicate(root, query, criteriaBuilder);
    }

    private Predicate getFilterPredicate(Root<FacultyMember> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        Join<FacultyMember, College> facultyMemberCollegeJoin = root.join("college");
//        Join<FacultyMember, Department> facultyMemberDepartmentJoin = root.join("department");

        System.out.println(filterCollege);
//        System.out.println(filterDepartment);

        Predicate x = criteriaBuilder.equal(facultyMemberCollegeJoin.get("id"), filterCollege);
//        Predicate y = criteriaBuilder.equal(facultyMemberDepartmentJoin.get("id"), filterDepartment);

        return criteriaBuilder.and(x);
    }

}
