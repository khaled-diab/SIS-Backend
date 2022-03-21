package com.sis.controller;

import com.sis.dto.section.SectionDTO;
import com.sis.dto.section.SectionRequestDTO;
import com.sis.entities.Section;
import com.sis.entities.mapper.CollegeMapper;
import com.sis.entities.mapper.DepartmentMapper;
import com.sis.entities.mapper.SectionMapper;
import com.sis.exception.SectionFieldNotUniqueException;
import com.sis.service.SectionService;
import com.sis.util.PageQueryUtil;
import com.sis.util.PageResult;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/sections")
@CrossOrigin(origins = ("*"))
@AllArgsConstructor
@Validated
public class SectionController extends BaseController<Section, SectionDTO> {

    private final SectionService sectionService;
    private final SectionMapper sectionMapper;
    private final CollegeMapper collegeMapper;
    private final DepartmentMapper departmentMapper;


    @RequestMapping(value = "/search/{pageNumber}/{size}", method = RequestMethod.POST)
    public ResponseEntity<PageResult<SectionDTO>> search(@PathVariable int pageNumber,
                                                         @PathVariable int size,
                                                         @RequestBody SectionRequestDTO sectionRequestDTO) {
        PageQueryUtil pageUtil = new PageQueryUtil(pageNumber, size);
        return new ResponseEntity<>(sectionService.search(pageUtil, sectionRequestDTO), HttpStatus.OK);
    }

//    @RequestMapping(value = "/save", method = RequestMethod.PUT)
//    public MessageResponse update(@RequestBody @Valid SectionDTO dto) {
//        if (this.sectionService.findSection(dto.getSectionNumber(),
//                collegeMapper.toEntity(dto.getCollegeDTO()),
//                departmentMapper.toEntity(dto.getDepartmentDTO())) != null) {
//            throw new SectionFieldNotUniqueException("sectionNumber", "Section Already Exists");
//        }
//        sectionService.save(sectionMapper.toEntity(dto));
//        return new MessageResponse("Item has been saved successfully");
//    }

    @RequestMapping(value = "/save", method = RequestMethod.PUT)
    public ResponseEntity<SectionDTO> save(@RequestBody @Valid SectionDTO dto) {
        if (this.sectionService.findSection(dto.getSectionNumber(),
                collegeMapper.toEntity(dto.getCollegeDTO()),
                departmentMapper.toEntity(dto.getDepartmentDTO())) != null) {
            throw new SectionFieldNotUniqueException("sectionNumber", "Section Already Exists");
        }
        SectionDTO section = sectionMapper.toDTO(sectionService.save(sectionMapper.toEntity(dto)));
        return new ResponseEntity<>(section, HttpStatus.OK);
    }

    @RequestMapping(value = "/countBySection/", method = RequestMethod.POST)
    public ResponseEntity<Integer> count(@RequestBody SectionDTO dto){
        Section section = sectionMapper.toEntity(dto);
        return new ResponseEntity<>(sectionService.countBySection(section), HttpStatus.OK);
    }

}
