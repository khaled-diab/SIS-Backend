package com.sis.controller;

import com.sis.dto.AcademicTermDTO;
import com.sis.entities.AcademicTerm;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/academicTerms")
@CrossOrigin("")
public class AcademicTermController extends BaseController<AcademicTerm, AcademicTermDTO> {

}
