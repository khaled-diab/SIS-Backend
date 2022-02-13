package com.sis.controller;

import com.sis.dto.LectureTypeDTO;
import com.sis.entities.LectureType;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
@RequestMapping(value = "/api/lectureTypes")
@CrossOrigin(origins = ("*"))
@AllArgsConstructor
public class LectureTypeController extends BaseController<LectureType, LectureTypeDTO> {


}
