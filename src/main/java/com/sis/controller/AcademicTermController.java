package com.sis.controller;

import com.sis.dto.AcademicTermDTO;
import com.sis.entity.AcademicTerm;
import com.sis.entity.mapper.AcademicTermMapper;
import com.sis.service.AcademicTermService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/academicTerms")
@Slf4j
public class AcademicTermController extends BaseController<AcademicTerm, AcademicTermDTO> {
    @Autowired
    private AcademicTermService academicTermService ;
    @Autowired
    private AcademicTermMapper academicTermMapper;

    @RequestMapping(value = "/addAcademicTerm",
            method = RequestMethod.POST)
    public ResponseEntity<String> addAcademicTerms(@RequestBody AcademicTermDTO academicTermDTO){
        if(academicTermDTO.getStart_date().after(academicTermDTO.getEnd_date())){
            log.info("Date not valid");
            return new ResponseEntity<>("Date not Valid" , HttpStatus.OK);
        }
        log.info("Date Vaild");
        academicTermService.save(academicTermMapper.toEntity(academicTermDTO));
        return new ResponseEntity<>("Added ", HttpStatus.OK);
    }
}
