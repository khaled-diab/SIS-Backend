package com.sis.dao.specification;

import com.sis.entities.College;
import com.sis.entities.Department;
import com.sis.entities.FacultyMember;
import com.sis.entities.Student;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;

public class StudentSpecification implements Specification<Student> {

    private String searchValue;

    private Long filterCollege;

    private Long filterDepartment;

    private Long filterLevel;

    public StudentSpecification(String searchValue, Long filterCollege, Long filterDepartment, long filterLevel){
        this.searchValue = searchValue;
        this.filterCollege = filterCollege;
        this.filterDepartment = filterDepartment;
        this.filterLevel= filterLevel;
    }

    public StudentSpecification() {
        this.searchValue = null;
        this.filterCollege = null;
        this.filterDepartment = null;
        this.filterLevel= null;
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
            Predicate x = criteriaBuilder.or(
                    criteriaBuilder.like(root.get("nameAr"), "%" + searchValue + "%"),
                    criteriaBuilder.like(root.get("nameEn"), "%" + searchValue + "%"),
                    criteriaBuilder.like(root.get("universityId"), "%" + searchValue + "%"),
                    criteriaBuilder.like(root.get("year"), "%" + searchValue + "%")
            );
            if (filterCollege == null && filterDepartment == null && filterLevel == null) {
                return x;
            }
            return criteriaBuilder.and(x, getFilterPredicate(root, query, criteriaBuilder));
        }
        return getFilterPredicate(root, query, criteriaBuilder);
    }

    private Predicate getFilterPredicate(Root<Student> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        Join<Student, College> facultyMemberCollegeJoin = root.join("collegeId");
        Join<Student, Department> facultyMemberDepartmentJoin = root.join("departmentId");

        System.out.println(filterCollege);
        System.out.println(filterDepartment);

        Predicate college = criteriaBuilder.equal(facultyMemberCollegeJoin.get("id"), filterCollege);
        if (filterDepartment == null){
            return college;
        }
        Predicate department = criteriaBuilder.equal(facultyMemberDepartmentJoin.get("id"), filterDepartment);

        return criteriaBuilder.and(college, department);
    }

}
