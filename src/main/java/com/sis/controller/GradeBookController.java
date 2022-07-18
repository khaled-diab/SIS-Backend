package com.sis.controller;

import com.sis.dto.gradeBook.GradeBookDTO;
import com.sis.dto.gradeBook.GradeBookRequestDTO;
import com.sis.dto.studentEnrollment.StudentEnrollmentDTO;
import com.sis.dto.studentEnrollment.StudentEnrollmentRequestDTO;
import com.sis.entity.GradeBook;
import com.sis.service.GradeBookService;
import com.sis.util.PageQueryUtil;
import com.sis.util.PageResult;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/gradeBook")
@AllArgsConstructor
public class GradeBookController extends BaseController<GradeBook, GradeBookDTO> {

    private final GradeBookService gradeBookService;

    @RequestMapping(value = "/filter/{pageNumber}/{size}", method = RequestMethod.POST)
    public ResponseEntity<PageResult<GradeBookDTO>> filter(@PathVariable int pageNumber,
                                                                   @PathVariable int size,
                                                                   @RequestBody GradeBookRequestDTO gradeBookRequestDTO) {
        PageQueryUtil pageUtil = new PageQueryUtil(pageNumber, size);
        return new ResponseEntity<>(gradeBookService.filter(pageUtil, gradeBookRequestDTO), HttpStatus.OK);
    }

}
