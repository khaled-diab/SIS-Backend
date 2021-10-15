package com.sis.controller;

import com.sis.dto.ClassroomDTO;
import com.sis.entities.Classroom;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/classroom")
public class ClassroomController extends BaseController<Classroom, ClassroomDTO>{
}
