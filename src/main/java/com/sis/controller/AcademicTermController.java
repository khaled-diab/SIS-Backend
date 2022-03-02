package com.sis.controller;
//package com.sis.controller;
//
//
//import com.sis.dto.BaseDTO;
//import com.sis.entities.mapper.AcademicYearMapper;
//import lombok.AllArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import com.sis.dto.AcademicTermDTO;
//import com.sis.entities.AcademicTerm;
//@RestController
//@RequestMapping("/api/AcademicTerm")
//@AllArgsConstructor
//public class AcademicTermController extends BaseController<AcademicTerm,AcademicTermDTO> {
////        @Autowired
//    private final AcademicYearMapper academicYearMapper;
//
//}


import com.sis.dto.AcademicTermDTO;
import com.sis.entities.AcademicTerm;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/academicTerms")
public class AcademicTermController extends BaseController<AcademicTerm, AcademicTermDTO> {


}
