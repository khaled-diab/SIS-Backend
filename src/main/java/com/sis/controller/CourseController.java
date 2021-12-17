package com.sis.controller;

import com.sis.dto.course.CourseDTO;
import com.sis.dto.course.CourseRequestDTO;
import com.sis.entities.Course;
import com.sis.entities.mapper.CourseMapper;
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


@RestController
@AllArgsConstructor
@CrossOrigin(origins = "'http://localhost:4200")
@RequestMapping(value = "/api/courses")
@Validated
public class CourseController extends BaseController<Course, CourseDTO> {

    private final CourseService courseService;


    private final CourseMapper courseMapper;

    @RequestMapping(value = "/search/{pageNumber}/{size}", method = RequestMethod.POST)
    public ResponseEntity<PageResult<CourseDTO>> search(@PathVariable int pageNumber,
                                                        @PathVariable int size,
                                                        @RequestBody CourseRequestDTO courseRequestDTO) {
        PageQueryUtil pageUtil = new PageQueryUtil(pageNumber, size);
        return new ResponseEntity<>(courseService.search(pageUtil, courseRequestDTO), HttpStatus.OK);
    }

    @RequestMapping(value = "/updateCourse", method = RequestMethod.PUT)
    public MessageResponse up(@RequestBody @Valid CourseDTO dto) {
        courseService.update(dto);
        return new MessageResponse("Item has been updated successfully");
    }

    @RequestMapping(value = "/save", method = RequestMethod.PUT)
    public MessageResponse update(@RequestBody @Valid CourseDTO dto) {
        courseService.save(courseMapper.toEntity(dto));
        return new MessageResponse("Item has been updated successfully");
    }

}



