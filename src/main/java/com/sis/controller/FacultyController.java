package com.sis.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.sis.dto.FacultyDTO;
import com.sis.entities.Faculty;

@RestController
@RequestMapping(value = "/api/faculties")
public class FacultyController extends BaseController<Faculty,FacultyDTO>{
		
}
