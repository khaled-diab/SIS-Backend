package com.sis.controller;

import com.sis.dto.MajorDTO;
import com.sis.entity.Major;
import com.sis.service.MajorService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/majors")
@AllArgsConstructor
public class MajorController extends BaseController<Major, MajorDTO> {
    private final MajorService majorService;

    @RequestMapping(value = "/majorsByDepartmentId/{departmentId}", method = RequestMethod.GET)
    public ResponseEntity<List<MajorDTO>> majorsByCollegeId(@PathVariable long departmentId) {
        List<MajorDTO> majorDTOS = this.majorService.getMajorsByDepartmentId(departmentId);
        return new ResponseEntity<>(majorDTOS, HttpStatus.OK);
    }
}
