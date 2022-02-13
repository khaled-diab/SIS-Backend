package com.sis.controller;

import com.sis.dto.section.SectionDTO;
import com.sis.entities.Section;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@Validated
@RequestMapping(value = "/api/sections")
@CrossOrigin(origins = ("*"))
@AllArgsConstructor
public class SectionController extends BaseController<Section, SectionDTO> {


}
