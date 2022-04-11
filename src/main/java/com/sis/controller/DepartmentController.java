package com.sis.controller;

import com.sis.dto.DepartmentDTO;
import com.sis.entity.Department;
import com.sis.entity.mapper.DepartmentMapper;
import com.sis.repository.Departmentrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController extends BaseController<Department, DepartmentDTO>{
    @Autowired
    private DepartmentMapper departmentMapper;
    @Autowired
    private Departmentrepository departmentrepository;

}
