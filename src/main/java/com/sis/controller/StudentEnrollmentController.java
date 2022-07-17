package com.sis.controller;

import com.sis.dto.student.StudentDTO;
import com.sis.dto.studentEnrollment.StudentArray;
import com.sis.dto.studentEnrollment.StudentEnrollmentDTO;
import com.sis.dto.studentEnrollment.StudentEnrollmentRequestDTO;
import com.sis.entity.StudentEnrollment;
import com.sis.entity.mapper.StudentEnrollmentMapper;
import com.sis.service.StudentEnrollmentService;
import com.sis.util.MessageResponse;
import com.sis.util.PageQueryUtil;
import com.sis.util.PageResult;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping(value = "/api/studentEnrollments")
@AllArgsConstructor
@Validated
public class StudentEnrollmentController extends BaseController<StudentEnrollment, StudentEnrollmentDTO> {

    private final StudentEnrollmentService studentEnrollmentService;
    private final StudentEnrollmentMapper studentEnrollmentMapper;

    @RequestMapping(value = "/search/{pageNumber}/{size}", method = RequestMethod.POST)
    public ResponseEntity<PageResult<StudentEnrollmentDTO>> search(@PathVariable int pageNumber,
                                                                   @PathVariable int size,
                                                                   @RequestBody StudentEnrollmentRequestDTO studentEnrollmentRequestDTO) {
        PageQueryUtil pageUtil = new PageQueryUtil(pageNumber, size);
        return new ResponseEntity<>(studentEnrollmentService.search(pageUtil, studentEnrollmentRequestDTO), HttpStatus.OK);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public MessageResponse update(@RequestBody @Valid StudentEnrollmentDTO dto) {
        studentEnrollmentService.save(studentEnrollmentMapper.toEntity(dto));
        return new MessageResponse("Item has been updated successfully");
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public MessageResponse save(@RequestBody StudentArray dto) {
        this.studentEnrollmentService.saveAll(this.studentEnrollmentService.save(dto));
        return new MessageResponse("Item has been saved successfully");
    }

}
