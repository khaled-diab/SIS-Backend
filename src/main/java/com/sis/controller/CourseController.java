package com.sis.controller;

import com.sis.dto.CourseDTO;
import com.sis.entities.Course;
import com.sis.entities.mapper.CourseMapper;
import com.sis.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/api/courses")
public class CourseController extends BaseController<Course, CourseDTO> {
    @Autowired
    CourseService courseService;

    @Autowired
    CourseMapper courseMapper;

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public List<CourseDTO> search(@RequestParam(value = "key") String key) {
        return courseMapper.toDTOs(courseService.search(key));
    }

}



