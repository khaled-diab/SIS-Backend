package com.sis.service;

import com.sis.entities.Department;
import com.sis.repository.DepartmentDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class DepartmentService extends BaseServiceImp<Department>{
    private final DepartmentDao departmentDao;
    public DepartmentService(DepartmentDao departmentDao){
        this.departmentDao = departmentDao ;
    }
    @Override
    public JpaRepository<Department, Long> Repository() {

        return  departmentDao;
    }
}
