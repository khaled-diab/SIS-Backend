package com.sis.controller;

import com.sis.dto.AcademicProgramDTO;
import com.sis.entity.AcademicProgram;
import com.sis.service.AcademicProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/academicPrograms")
@CrossOrigin("")
public class AcademicProgramController extends BaseController<AcademicProgram, AcademicProgramDTO> {
    @Autowired
    private AcademicProgramService academicProgramService;

    @RequestMapping(value = "/academicProgramsByDepartmentId/{departmentId}", method = RequestMethod.GET)
    public ResponseEntity<List<AcademicProgramDTO>> academicProgramsByDepartmentId(@PathVariable long departmentId) {
        List<AcademicProgramDTO> academicProgramDTOS = this.academicProgramService.academicProgramsByDepartmentId(departmentId);
        return new ResponseEntity<>(academicProgramDTOS, HttpStatus.OK);
    }

}
