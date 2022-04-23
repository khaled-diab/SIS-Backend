package com.sis.controller;

import com.sis.dto.AcademicTermDTO;
import com.sis.dto.section.SectionDTO;
import com.sis.dto.section.SectionRequestDTO;
import com.sis.dto.section.Section_Course;
import com.sis.entity.AcademicTerm;
import com.sis.entity.Section;
import com.sis.entity.mapper.AcademicTermMapper;
import com.sis.entity.mapper.SectionMapper;
import com.sis.exception.SectionFieldNotUniqueException;
import com.sis.service.AcademicTermService;
import com.sis.service.SectionService;
import com.sis.util.PageQueryUtil;
import com.sis.util.PageResult;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;

@RestController
@RequestMapping(value = "/api/sections")
@AllArgsConstructor
@Validated
public class SectionController extends BaseController<Section, SectionDTO> {

    private final SectionService sectionService;
    private final SectionMapper sectionMapper;

    private AcademicTermService academicTermService;
    private AcademicTermMapper academicTermMapper;

    @RequestMapping(value = "/search/{pageNumber}/{size}", method = RequestMethod.POST)
    public ResponseEntity<PageResult<SectionDTO>> search(@PathVariable int pageNumber,
                                                         @PathVariable int size,
                                                         @RequestBody SectionRequestDTO sectionRequestDTO) {
        PageQueryUtil pageUtil = new PageQueryUtil(pageNumber, size);
        return new ResponseEntity<>(sectionService.search(pageUtil, sectionRequestDTO), HttpStatus.OK);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity<SectionDTO> save(@RequestBody @Valid SectionDTO dto) {
        if (this.sectionService.findSection(dto.getSectionNumber(), dto.getCollegeDTO().getId(),
                dto.getDepartmentDTO().getId()) != null) {
            throw new SectionFieldNotUniqueException("sectionNumber", "Section Already Exists");
        }
        SectionDTO section = sectionMapper.toDTO(sectionService.save(sectionMapper.toEntity(dto)));
        return new ResponseEntity<>(section, HttpStatus.OK);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public ResponseEntity<SectionDTO> update(@RequestBody @Valid SectionDTO dto) {
        SectionDTO section = sectionMapper.toDTO(sectionService.save(sectionMapper.toEntity(dto)));
        return new ResponseEntity<>(section, HttpStatus.OK);
    }

    // Abdo.Amr
    @RequestMapping(value = "/getFacultyMemberSections/{facultyMemberId}", method = RequestMethod.GET)
    public ResponseEntity<ArrayList<SectionDTO>> getFacultyMemberSections(@PathVariable long facultyMemberId) {
        AcademicTerm academicTerm = this.academicTermService.getCurrentAcademicTerm();
        AcademicTermDTO academicTermDTO = this.academicTermMapper.toDTO(academicTerm);

        ArrayList<SectionDTO> sectionDTOs = this.sectionService.findFacultyMemberSections(academicTermDTO.getAcademicYearDTO().getId(), academicTermDTO.getId(), facultyMemberId);
        return new ResponseEntity<>(sectionDTOs, HttpStatus.OK);
    }

    // Abdo.Amr
    @RequestMapping(value = "/getFacultyMemberSections_courses/{facultyMemberId}", method = RequestMethod.GET)
    public ResponseEntity<ArrayList<Section_Course>> getFacultyMemberSections_Courses(@PathVariable long facultyMemberId) {
        AcademicTerm academicTerm = this.academicTermService.getCurrentAcademicTerm();
        AcademicTermDTO academicTermDTO = this.academicTermMapper.toDTO(academicTerm);

        ArrayList<Section_Course> sectionDTOs = this.sectionService.findFacultyMemberSections_courses(academicTermDTO.getAcademicYearDTO().getId(), academicTermDTO.getId(), facultyMemberId);
        return new ResponseEntity<>(sectionDTOs, HttpStatus.OK);
    }
}
