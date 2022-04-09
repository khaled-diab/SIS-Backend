package com.sis.controller;

import com.sis.dto.MajorDTO;
import com.sis.entity.Major;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/majors")
@CrossOrigin(origins = ("*"))
@AllArgsConstructor
public class MajorController extends BaseController<Major, MajorDTO> {


}
