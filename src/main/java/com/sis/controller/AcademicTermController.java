package com.sis.controller;



import com.sis.dto.AcademicTermDTO;
import com.sis.entities.AcademicTerm;
import com.sis.entities.mapper.AcademicTermMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/academicTerms")
public class AcademicTermController extends BaseController<AcademicTerm, AcademicTermDTO> {
    @Autowired
    private AcademicTermMapper academicTermMapper ;
}
