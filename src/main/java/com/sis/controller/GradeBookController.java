package com.sis.controller;

import com.sis.dto.course.CourseDTO;
import com.sis.dto.gradeBook.GradeBookDTO;
import com.sis.dto.gradeBook.GradeBookRequestDTO;
import com.sis.dto.student.StudentDTO;
import com.sis.entity.GradeBook;
import com.sis.entity.mapper.GradeBookMapper;
import com.sis.repository.GradeBookRepository;
import com.sis.service.GradeBookService;
import com.sis.util.MessageResponse;
import com.sis.util.PageQueryUtil;
import com.sis.util.PageResult;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/gradeBook")
@AllArgsConstructor
public class GradeBookController extends BaseController<GradeBook, GradeBookDTO> {

    private final GradeBookService gradeBookService;
    private final GradeBookMapper gradeBookMapper;
    private final GradeBookRepository gradeBookRepository;

    @RequestMapping(value = "/filter/{pageNumber}/{size}", method = RequestMethod.POST)
    public ResponseEntity<PageResult<GradeBookDTO>> filter(@PathVariable int pageNumber,
                                                                   @PathVariable int size,
                                                                   @RequestBody GradeBookRequestDTO gradeBookRequestDTO) {
        PageQueryUtil pageUtil = new PageQueryUtil(pageNumber, size);
        return new ResponseEntity<>(gradeBookService.filter(pageUtil, gradeBookRequestDTO), HttpStatus.OK);
    }

    @RequestMapping(value = "/courseStudents/{pageNumber}/{size}/{courseId}", method = RequestMethod.GET)
    public ResponseEntity<PageResult<StudentDTO>> getCourseStudents(@PathVariable int pageNumber,
                                                                    @PathVariable int size,
                                                                    @PathVariable Long courseId) {
        PageQueryUtil pageUtil = new PageQueryUtil(pageNumber, size);
        return new ResponseEntity<>(this.gradeBookService.getStudentsByCourseId(pageUtil,courseId), HttpStatus.OK);
    }

    @RequestMapping(value = "/coursesByFacultyMemberId/{termId}/{facultyMemberId}", method = RequestMethod.GET)
    public ResponseEntity<List<CourseDTO>> getCoursesByFacultyMember(@PathVariable Long termId,
                                                                     @PathVariable Long facultyMemberId) {
        List<CourseDTO> courseDTOS = this.gradeBookService.getCoursesByFacultyMemberId(termId, facultyMemberId);
        return new ResponseEntity<>(courseDTOS, HttpStatus.OK);
    }

    @RequestMapping(value = "/updateGradeBooks/", method = RequestMethod.POST)
    public ResponseEntity<MessageResponse> updateGradeBooks(@RequestBody List<GradeBookDTO> gradeBookDTOS) {
        this.gradeBookService.saveAll(this.gradeBookMapper.toEntities(gradeBookDTOS));
        return new ResponseEntity<>(new MessageResponse("GradeBooks Updated Successfully"), HttpStatus.OK);
    }

}
