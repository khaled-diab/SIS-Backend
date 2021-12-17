package com.sis.controller;

import com.sis.dto.DepartmentDTO;
import com.sis.entities.AcademicYear;
import com.sis.entities.Department;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController extends BaseController<Department, DepartmentDTO> {


}
