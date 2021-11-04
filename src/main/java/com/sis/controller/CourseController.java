package com.sis.controller;

import com.sis.dto.CourseDTO;
import com.sis.entities.Course;
import com.sis.entities.mapper.CourseMapper;
import com.sis.service.CourseService;
import com.sis.util.MessageResponse;
import com.sis.util.PageQueryUtil;
import com.sis.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/api/courses")
public class CourseController extends BaseController<Course, CourseDTO> {
    @Autowired
    CourseService courseService;

    @Autowired
    CourseMapper courseMapper;

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public ResponseEntity<PageResult<CourseDTO>> search(@RequestParam(value = "key") String key,@RequestBody PageQueryUtil pageUtil) {

        return new ResponseEntity<>(courseService.search(pageUtil, key), HttpStatus.OK);
    }

    @RequestMapping(value = "/updateCourse", method =RequestMethod.PUT)
    public MessageResponse up(@RequestBody CourseDTO dto) {
        courseService.update(dto);
        return new MessageResponse("Item has been updated successfully");
    }


}



