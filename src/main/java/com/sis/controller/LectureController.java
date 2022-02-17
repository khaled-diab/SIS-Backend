package com.sis.controller;

import com.sis.dto.lecture.LectureDTO;
import com.sis.entities.Lecture;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/lectures")
public class LectureController extends BaseController<Lecture, LectureDTO>{

}
