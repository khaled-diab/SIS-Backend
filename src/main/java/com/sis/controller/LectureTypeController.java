package com.sis.controller;

import com.sis.dto.LectureTypeDTO;
import com.sis.entity.LectureType;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/lectureTypes")
@AllArgsConstructor
public class LectureTypeController extends BaseController<LectureType, LectureTypeDTO> {


}
