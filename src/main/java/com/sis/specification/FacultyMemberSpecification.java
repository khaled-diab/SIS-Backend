package com.sis.specification;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.sis.entities.FacultyMember;

public class FacultyMemberSpecification implements Specification<FacultyMember> {

    private String key;

    public FacultyMemberSpecification(String key) {
        this.key = key;
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
        Predicate x = criteriaBuilder.or(
                criteriaBuilder.like(root.get("nameAr"), "%" + key + "%"),
                criteriaBuilder.like(root.get("nameEn"), "%" + key + "%"),
                criteriaBuilder.like(root.get("degree"), "%" + key + "%"),
                criteriaBuilder.like(root.get("universityMail"), "%" + key + "%")
//                criteriaBuilder.like(root.get("college"), "%" + key + "%"),
//                criteriaBuilder.like(root.get("department"), "%" + key + "%")
        );

        return x;
    }
}
