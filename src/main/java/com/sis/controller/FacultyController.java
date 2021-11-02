package com.sis.controller;

import com.sis.entities.FacultyMember;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.sis.dto.FacultyDTO;


@RestController
@RequestMapping(value = "/api/faculties")
public class FacultyController extends BaseController<FacultyMember,FacultyDTO>{
		
}
