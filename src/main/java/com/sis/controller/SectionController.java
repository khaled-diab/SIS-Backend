package com.sis.controller;

import com.sis.dto.section.SectionDTO;
import com.sis.dto.section.SectionRequestDTO;
import com.sis.entities.Section;
import com.sis.service.SectionService;
import com.sis.util.PageQueryUtil;
import com.sis.util.PageResult;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/api/sections")
@CrossOrigin(origins = ("*"))
@AllArgsConstructor
public class SectionController extends BaseController<Section, SectionDTO> {

    private final SectionService sectionService;

    @RequestMapping(value = "/search/{pageNumber}/{size}", method = RequestMethod.POST)
    public ResponseEntity<PageResult<SectionDTO>> search(@PathVariable int pageNumber,
                                                         @PathVariable int size,
                                                         @RequestBody SectionRequestDTO sectionRequestDTO) {
        PageQueryUtil pageUtil = new PageQueryUtil(pageNumber, size);
        return new ResponseEntity<>(sectionService.search(pageUtil, sectionRequestDTO), HttpStatus.OK);
    }

}
