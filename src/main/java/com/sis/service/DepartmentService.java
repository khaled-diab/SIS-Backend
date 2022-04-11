package com.sis.service;

import com.sis.entity.Department;
import com.sis.repository.Departmentrepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class DepartmentService extends BaseServiceImp<Department> {
    private final Departmentrepository departmentrepository;

    public DepartmentService(Departmentrepository departmentrepository) {
        this.departmentrepository = departmentrepository;
    }

    @Override
    public JpaRepository<Department, Long> Repository() {

        return departmentrepository;
    }
}
