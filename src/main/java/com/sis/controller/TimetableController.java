package com.sis.controller;

import com.sis.dto.timetable.TimetableDTO;
import com.sis.entities.Timetable;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
@RequestMapping(value = "/api/timetables")
@CrossOrigin(origins = ("*"))
@AllArgsConstructor
public class TimetableController extends BaseController<Timetable, TimetableDTO> {


}
