package com.sis.controller;

import com.sis.dto.AcademicTermDTO;
import com.sis.entity.AcademicTerm;
import com.sis.entity.mapper.AcademicTermMapper;
import com.sis.service.AcademicTermService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/academicTerms")
public class AcademicTermController extends BaseController<AcademicTerm, AcademicTermDTO> {

    @Autowired
    private AcademicTermService academicTermService;
    @Autowired
    private AcademicTermMapper academicTermMapper;

    @GetMapping("/getCurrentTerm")
    public ResponseEntity<AcademicTermDTO> getCurrentTerm(){
        return new ResponseEntity<>(this.academicTermMapper.toDTO(this.academicTermService.getCurrentAcademicTerm()), HttpStatus.OK);
    }
}
