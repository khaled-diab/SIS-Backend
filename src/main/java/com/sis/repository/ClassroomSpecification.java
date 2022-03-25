package com.sis.repository;

import com.sis.entities.Classroom;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class ClassroomSpecification implements Specification<Classroom> {
    private final String key;

    public ClassroomSpecification(String key) {
        this.key = key;
    }

    @Override
    public Specification<Classroom> and(Specification<Classroom> other) {
        return Specification.super.and(other);
    }

    @Override
    public Specification<Classroom> or(Specification<Classroom> other) {
        return Specification.super.or(other);
    }

    @Override
    public Predicate toPredicate(Root<Classroom> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        Predicate x = criteriaBuilder.or(
                criteriaBuilder.like(root.get("name_ar"), "%" + key + "%"),
                criteriaBuilder.like(root.get("name_en"), "%" + key + "%"),
                criteriaBuilder.like(root.get("code"), "%" + key + "%")

        );
        int capacity;

        try {
            capacity = Integer.parseInt(key);
        } catch (Exception ex) {
            capacity = -1;
        }

        Predicate y = criteriaBuilder.or(
                criteriaBuilder.equal(root.get("capacity"), capacity)
        );

        return criteriaBuilder.or(x, y);
    }

}
