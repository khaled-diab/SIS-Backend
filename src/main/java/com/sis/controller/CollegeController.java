package com.sis.controller;

import com.sis.dto.college.CollegeDTO;
import com.sis.dto.college.GeneralSearchRequest;
import com.sis.entity.College;
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

    @PostMapping(value = "/findAll/{page}/{size}")
    public PageResult<CollegeDTO> findAll(@PathVariable Integer page, @PathVariable Integer size, @RequestBody GeneralSearchRequest generalSearchRequest) {
        return collegeService.getCollegesPage(page, size, generalSearchRequest);
    }

    @GetMapping(value = "/deleteCollege/{id}")
    public MessageResponse deleteCollege(@PathVariable(value = "id") Long id) {
        collegeService.deleteById(id);
        return new MessageResponse("College has been deleted successfully");
    }

    @GetMapping(value = "/checkCollegeCode/{code}")
    public MessageResponse checkCollegeCode(@PathVariable(value = "code") String code) {
        return collegeService.checkCollegeCode(code);
    }
}
