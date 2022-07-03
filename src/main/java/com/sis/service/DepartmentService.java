package com.sis.service;

import com.sis.entity.Department;
import com.sis.repository.DepartmentRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DepartmentService extends BaseServiceImp<Department> {
    private final DepartmentRepository departmentrepository;


    @Override
    public JpaRepository<Department, Long> Repository() {
        return departmentrepository;
    }
}
