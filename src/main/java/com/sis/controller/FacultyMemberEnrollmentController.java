package com.sis.controller;

import com.sis.dto.FacultyMemberEnrollment.FacultyMemberEnrollmentDTO;
import com.sis.dto.FacultyMemberEnrollment.FacultyMemberEnrollmentRequestDTO;
import com.sis.entities.FacultyMemberEnrollment;
import com.sis.service.FacultyMemberEnrollmentService;
import com.sis.util.PageQueryUtil;
import com.sis.util.PageResult;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/facultyMemberEnrollments")
@CrossOrigin(origins = ("*"))
@AllArgsConstructor
public class FacultyMemberEnrollmentController extends BaseController<FacultyMemberEnrollment, FacultyMemberEnrollmentDTO> {

    private final FacultyMemberEnrollmentService facultyMemberEnrollmentService;

    @RequestMapping(value = "/search/{pageNumber}/{size}", method = RequestMethod.POST)
    public ResponseEntity<PageResult<FacultyMemberEnrollmentDTO>> search(@PathVariable int pageNumber,
                                                                         @PathVariable int size,
                                                                         @RequestBody FacultyMemberEnrollmentRequestDTO facultyMemberEnrollmentRequestDTO) {
        PageQueryUtil pageUtil = new PageQueryUtil(pageNumber, size);
        return new ResponseEntity<>(facultyMemberEnrollmentService.search(pageUtil, facultyMemberEnrollmentRequestDTO), HttpStatus.OK);
    }
}
