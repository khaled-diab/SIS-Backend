package com.sis.controller;

import com.sis.dto.gradeBook.GradeBookDTO;
import com.sis.entity.GradeBook;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/GradeBook")
@AllArgsConstructor
public class GradeBookController extends BaseController<GradeBook, GradeBookDTO> {

}
