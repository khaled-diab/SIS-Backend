package com.sis.service;

import com.sis.dao.DepartmentDao;
import com.sis.entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartmentService extends BaseServiceImp<Department>{
    private  DepartmentDao departmentDao ;
    public DepartmentService(DepartmentDao departmentDao){
        this.departmentDao = departmentDao ;
    }
    @Override
    public JpaRepository<Department, Long> Repository() {

        return  departmentDao;
    }
}
