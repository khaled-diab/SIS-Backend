package com.sis.controller;

import com.sis.dto.studentEnrollment.StudentEnrollmentDTO;
import com.sis.entities.StudentEnrollment;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@Validated
@RequestMapping(value = "/api/studentEnrollment")
//@CrossOrigin(origins = ("*"))
//@AllArgsConstructor
public class StudentEnrollmentController extends BaseController<StudentEnrollment, StudentEnrollmentDTO> {


}
