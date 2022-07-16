package com.sis.repository;

import com.sis.entity.Building;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class BuildingSpecification implements Specification<Building> {

    private final String key;

    public BuildingSpecification(String key) {
        this.key = key;
    }

    @Override
    public Specification<Building> and(Specification<Building> other) {
        return Specification.super.and(other);
    }

    @Override
    public Specification<Building> or(Specification<Building> other) {
        return Specification.super.or(other);
    }

    @Override
    public Predicate toPredicate(Root<Building> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        Predicate x = criteriaBuilder.or(
                criteriaBuilder.like(root.get("name"), "%" + key + "%"),
                criteriaBuilder.like(root.get("code"), "%" + key + "%")
        );
        return x;
    }
}
