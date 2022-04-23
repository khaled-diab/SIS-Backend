package com.sis.controller;

import com.sis.dto.StudyTypeDTO;
import com.sis.entity.StudyType;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/studyTypes")
@AllArgsConstructor
public class StudyTypeController extends BaseController<StudyType, StudyTypeDTO> {


}
