package com.sis.controller;

import com.sis.dto.major.MajorDTO;
import com.sis.dto.major.MajorRequestDTO;
import com.sis.dto.major.MajorTableRecordsDTO;
import com.sis.dto.studentEnrollment.StudentEnrollmentDTO;
import com.sis.entity.Major;
import com.sis.entity.mapper.MajorMapper;
import com.sis.service.MajorService;
import com.sis.util.MessageResponse;
import com.sis.util.PageQueryUtil;
import com.sis.util.PageResult;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/majors")
@AllArgsConstructor
public class MajorController extends BaseController<Major, MajorDTO> {
    private final MajorService majorService;
    private final MajorMapper majorMapper;

    @RequestMapping(value = "/majorsByDepartmentId/{departmentId}", method = RequestMethod.GET)
    public ResponseEntity<List<MajorDTO>> majorsByCollegeId(@PathVariable long departmentId) {
        List<MajorDTO> majorDTOS = this.majorService.getMajorsByDepartmentId(departmentId);
        return new ResponseEntity<>(majorDTOS, HttpStatus.OK);
    }

    @RequestMapping(value = "/filter/{pageNumber}/{size}", method = RequestMethod.POST)
    public ResponseEntity<PageResult<MajorTableRecordsDTO>> filter(@PathVariable int pageNumber,
                                                                   @PathVariable int size,
                                                                   @RequestBody MajorRequestDTO
                                                                           majorRequestDTO) {
        PageQueryUtil pageUtil = new PageQueryUtil(pageNumber, size);
        return new ResponseEntity<>(majorService.filter(pageUtil, majorRequestDTO), HttpStatus.OK);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public MessageResponse update(@RequestBody @Valid MajorDTO dto) {
        majorService.save(majorMapper.toEntity(dto));
        return new MessageResponse("Major has been updated successfully");
    }
}
