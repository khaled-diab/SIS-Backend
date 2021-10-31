package com.sis.controller;

import com.sis.dto.college.CollegeDTO;
import com.sis.dto.college.CollegeFilterDTO;
import com.sis.entities.College;
import com.sis.entities.mapper.CollegeMapper;
import com.sis.service.CollegeService;
import com.sis.util.PageResult;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/colleges")
@AllArgsConstructor
public class CollegeController extends BaseController<College, CollegeDTO> {

    private final CollegeService collegeService;
    private final CollegeMapper collegeMapper;


    @RequestMapping(value = "/findAll/{page}/{size}", method = RequestMethod.POST)
    public PageResult<CollegeDTO> findAll(@PathVariable Integer page,
                                          @PathVariable Integer size,
                                          @RequestBody CollegeFilterDTO collegeFilterDTO) {
        return collegeService.getCollegesPage(page,size,collegeFilterDTO);
    }
}
