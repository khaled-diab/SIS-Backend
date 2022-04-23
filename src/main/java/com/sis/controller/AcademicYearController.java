package com.sis.controller;

import com.sis.dto.AcademicYearDTO;
import com.sis.entity.AcademicYear;
import com.sis.entity.mapper.AcademicYearMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/academicYears")
@CrossOrigin("")
public class AcademicYearController  extends  BaseController<AcademicYear, AcademicYearDTO> {
    @Autowired
    private AcademicYearMapper academicYearMapper ;

}
