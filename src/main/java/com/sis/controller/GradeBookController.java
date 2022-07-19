package com.sis.controller;

import com.sis.dto.AcademicTermDTO;
import com.sis.dto.course.CourseDTO;
import com.sis.dto.gradeBook.GradeBookDTO;
import com.sis.dto.gradeBook.GradeBookRequestDTO;
import com.sis.dto.student.StudentDTO;
import com.sis.dto.studentEnrollment.StudentEnrollmentDTO;
import com.sis.dto.studentEnrollment.StudentEnrollmentRequestDTO;
import com.sis.entity.AcademicTerm;
import com.sis.entity.GradeBook;
import com.sis.service.GradeBookService;
import com.sis.util.PageQueryUtil;
import com.sis.util.PageResult;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

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

    @RequestMapping(value = "/courseStudents/{courseId}", method = RequestMethod.GET)
    public ResponseEntity<List<StudentDTO>> getCourseStudents(@PathVariable Long courseId) {
        List<StudentDTO> studentDTOS = this.gradeBookService.getStudentsByCourseId(courseId);
        return new ResponseEntity<>(studentDTOS, HttpStatus.OK);
    }

    @RequestMapping(value = "/coursesByFacultyMemberId/{termId}/{facultyMemberId}", method = RequestMethod.GET)
    public ResponseEntity<List<CourseDTO>> getCoursesByFacultyMember(@PathVariable Long termId,
                                                                     @PathVariable Long facultyMemberId) {
        List<CourseDTO> courseDTOS = this.gradeBookService.getCoursesByFacultyMemberId(termId, facultyMemberId);
        return new ResponseEntity<>(courseDTOS, HttpStatus.OK);
    }

}
