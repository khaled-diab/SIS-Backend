package com.sis.controller;

import com.sis.dto.StudyTypeDTO;
import com.sis.entities.StudyType;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
@RequestMapping(value = "/api/studyTypes")
@CrossOrigin(origins = ("*"))
@AllArgsConstructor
public class StudyTypeController extends BaseController<StudyType, StudyTypeDTO> {


}
