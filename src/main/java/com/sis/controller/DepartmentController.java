package com.sis.controller;

import com.sis.dao.DepartmentDao;
import com.sis.dto.DepartmentDTO;
import com.sis.entities.Department;
import com.sis.entities.mapper.DepartmentMapper;
import com.sis.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController extends BaseController<Department, DepartmentDTO>{
    @Autowired
    private DepartmentMapper departmentMapper;
    @Autowired
    private DepartmentDao departmentDao ;


}
