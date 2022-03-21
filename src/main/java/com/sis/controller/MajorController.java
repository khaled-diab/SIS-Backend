package com.sis.controller;

import com.sis.dto.MajorDTO;
import com.sis.entities.Major;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/majors")
@CrossOrigin(origins = ("*"))
@AllArgsConstructor
public class MajorController extends BaseController<Major, MajorDTO> {


}
