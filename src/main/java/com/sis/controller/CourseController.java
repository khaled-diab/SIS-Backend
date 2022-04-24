package com.sis.controller;

import com.sis.dto.AcademicTermDTO;
import com.sis.dto.course.CourseDTO;
import com.sis.dto.course.CourseRequestDTO;
import com.sis.entity.AcademicTerm;
import com.sis.entity.Course;
import com.sis.entity.mapper.AcademicTermMapper;
import com.sis.entity.mapper.CourseMapper;
import com.sis.service.AcademicTermService;
import com.sis.service.CourseService;
import com.sis.util.MessageResponse;
import com.sis.util.PageQueryUtil;
import com.sis.util.PageResult;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;


@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/courses")
@Validated
public class CourseController extends BaseController<Course, CourseDTO> {

    private final CourseService courseService;


    private final CourseMapper courseMapper;

    //UC011
    private AcademicTermService academicTermService;

    //UC011
    private AcademicTermMapper academicTermMapper;

    @RequestMapping(value = "/search/{pageNumber}/{size}", method = RequestMethod.POST)
    public ResponseEntity<PageResult<CourseDTO>> search(@PathVariable int pageNumber,
                                                        @PathVariable int size,
                                                        @RequestBody CourseRequestDTO courseRequestDTO) {
        PageQueryUtil pageUtil = new PageQueryUtil(pageNumber, size);
        return new ResponseEntity<>(courseService.search(pageUtil, courseRequestDTO), HttpStatus.OK);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public MessageResponse update(@RequestBody @Valid CourseDTO dto) {
        courseService.save(courseMapper.toEntity(dto));
        return new MessageResponse("Item has been updated successfully");
    }

    //Abdo.Amr
    @RequestMapping(
            value = "/studentCourses/{studentId}",
            method = RequestMethod.GET
    )
    public ResponseEntity<Collection<CourseDTO>> getStudentCourses(@PathVariable long studentId) {

        AcademicTerm academicTerm = this.academicTermService.getCurrentAcademicTerm();
        AcademicTermDTO academicTermDTO = this.academicTermMapper.toDTO(academicTerm);
        Collection<CourseDTO> courseDTOS = this.courseService.getStudentCourses(
                academicTermDTO.getAcademicYearDTO().getId(),
                academicTermDTO.getId(),
                studentId);
        return new ResponseEntity<>(courseDTOS, HttpStatus.OK);
    }

    //Abdo.Amr
    @RequestMapping(
            value = "/facultyMemberCourses/{courseId}",
            method = RequestMethod.GET
    )
    public ResponseEntity<Collection<CourseDTO>> findFacultyMemberCourses(@PathVariable long courseId) {

        AcademicTerm academicTerm = this.academicTermService.getCurrentAcademicTerm();
        AcademicTermDTO academicTermDTO = this.academicTermMapper.toDTO(academicTerm);
        Collection<CourseDTO> courseDTOS = this.courseService.findFacultyMemberCourses(
                academicTermDTO.getAcademicYearDTO().getId(),
                academicTermDTO.getId(),
                courseId);
        return new ResponseEntity<>(courseDTOS, HttpStatus.OK);
        }
     }



