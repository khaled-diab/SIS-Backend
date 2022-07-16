package com.sis.controller;

import com.sis.dto.AcademicTermDTO;
import com.sis.dto.AcademicYearDTO;
import com.sis.entity.AcademicYear;
import com.sis.entity.mapper.AcademicTermMapper;
import com.sis.entity.mapper.AcademicYearMapper;
import com.sis.service.AcademicTermService;
import com.sis.service.AcademicYearService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/academicYears")
@CrossOrigin("")
@Slf4j
public class AcademicYearController  extends  BaseController<AcademicYear, AcademicYearDTO> {


    @Autowired
    private AcademicYearService academicYearService ;
    @Autowired
    private AcademicYearMapper academicYearMapper;

    @RequestMapping(value = "/addAcademicYear",
            method = RequestMethod.POST)
    public ResponseEntity<String> addAcademicYear(@RequestBody AcademicYearDTO academicYearDTO){
        if(academicYearDTO.getStart_date().after(academicYearDTO.getEnd_date())){
            log.info("Date not valid");
            return new ResponseEntity<>("Date not Valid" , HttpStatus.OK);
        }
        log.info("Date Vaild");
        academicYearService.save(academicYearMapper.toEntity(academicYearDTO));
        return new ResponseEntity<>("Added ", HttpStatus.OK);
    }

}
