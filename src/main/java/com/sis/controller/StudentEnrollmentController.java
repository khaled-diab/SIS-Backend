package com.sis.controller;

import com.sis.dto.studentEnrollment.StudentEnrollmentDTO;
import com.sis.dto.studentEnrollment.StudentEnrollmentRequestDTO;
import com.sis.entities.StudentEnrollment;
import com.sis.service.StudentEnrollmentService;
import com.sis.util.PageQueryUtil;
import com.sis.util.PageResult;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/studentEnrollment")
@CrossOrigin(origins = ("*"))
@AllArgsConstructor
public class StudentEnrollmentController extends BaseController<StudentEnrollment, StudentEnrollmentDTO> {

    private final StudentEnrollmentService studentEnrollmentService;

    @RequestMapping(value = "/search/{pageNumber}/{size}", method = RequestMethod.POST)
    public ResponseEntity<PageResult<StudentEnrollmentDTO>> search(@PathVariable int pageNumber,
                                                                   @PathVariable int size,
                                                                   @RequestBody StudentEnrollmentRequestDTO studentEnrollmentRequestDTO) {
        PageQueryUtil pageUtil = new PageQueryUtil(pageNumber, size);
        return new ResponseEntity<>(studentEnrollmentService.search(pageUtil, studentEnrollmentRequestDTO), HttpStatus.OK);
    }

}
