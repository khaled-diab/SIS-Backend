package com.sis.controller;

import com.sis.dto.DepartmentDTO;
import com.sis.entity.Department;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController extends BaseController<Department, DepartmentDTO>{


}
