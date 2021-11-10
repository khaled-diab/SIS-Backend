package com.sis.dao.specification;

import com.sis.dto.CourseDTO;
import com.sis.entities.Course;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class CourseSpecification implements Specification<Course> {

    private String key;

    public CourseSpecification(String key) {
        this.key = key;
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
       Predicate x = criteriaBuilder.or(
                criteriaBuilder.like(root.get("nameAr"), "%" + key + "%"),
                criteriaBuilder.like(root.get("nameEn"), "%" + key + "%"),
                criteriaBuilder.like(root.get("code"), "%" + key + "%")
        );
       float f1;

       try{
           f1= Float.parseFloat(key);
       }catch (Exception ex){
           f1=-1;
       }
       Predicate y =  criteriaBuilder.or(
               criteriaBuilder.equal(root.get("totalHours"), f1),
               criteriaBuilder.equal(root.get("finalGrade"), f1)
       );

        return criteriaBuilder.or(x,y);


    }
}


