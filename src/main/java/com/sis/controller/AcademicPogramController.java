package com.sis.controller;

import com.sis.dto.AcademicProgramDTO;
import com.sis.entity.AcademicProgram;
import com.sis.entity.mapper.AcademicProgramMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/api/academicPrograms")
public class AcademicPogramController extends BaseController<AcademicProgram , AcademicProgramDTO>{
    @Autowired
    private AcademicProgramMapper academicProgramMapper;

}
