package com.sis.controller;

import com.sis.dto.FacultyMemberEnrollment.FacultyMemberEnrollmentDTO;
import com.sis.entities.FacultyMemberEnrollment;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
@RequestMapping(value = "/api/facultyMemberEnrollment")
@CrossOrigin(origins = ("*"))
@AllArgsConstructor
public class FacultyMemberEnrollmentController extends BaseController<FacultyMemberEnrollment, FacultyMemberEnrollmentDTO> {


}
