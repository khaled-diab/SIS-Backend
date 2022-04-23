package com.sis.controller;

import com.sis.dto.college.CollegeDTO;
import com.sis.dto.college.CollegeRequestDTO;
import com.sis.entity.College;
import com.sis.entity.mapper.CollegeMapper;
import com.sis.service.CollegeService;
import com.sis.util.MessageResponse;
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
                                          @RequestBody CollegeRequestDTO collegeRequestDTO) {
        return collegeService.getCollegesPage(page,size,collegeRequestDTO);
    }

    @RequestMapping(value = "/deleteCollege/{id}", method = RequestMethod.DELETE)
    public MessageResponse deleteCollege(@PathVariable(value = "id") Long id) {
        collegeService.deleteById(id);
        return new MessageResponse("Item has been deleted successfully");
    }
}
