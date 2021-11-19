package com.sis.dao.specification;

import com.sis.entities.College;
import com.sis.entities.Course;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;

public class CourseSpecification implements Specification<Course> {

    private String searchValue;

    private Long filterCollege;

    public CourseSpecification(String searchValue, Long filterCollege) {
        this.searchValue = searchValue;
        this.filterCollege = filterCollege;
    }

    public CourseSpecification() {
        this.searchValue = null;
        this.filterCollege = null;
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
            if (filterCollege == null) {
                return criteriaBuilder.or(x, y);
            }
            return criteriaBuilder.and(criteriaBuilder.or(x, y),
                    getFilterPredicate(root, query, criteriaBuilder));
        }

        return getFilterPredicate(root, query, criteriaBuilder);

    }

    private Predicate getFilterPredicate(Root<Course> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        Join<Course, College> courseCollegeJoin = root.join("college");
        System.out.println(filterCollege);
        return criteriaBuilder.equal(courseCollegeJoin.get("id"), filterCollege);
    }
}



