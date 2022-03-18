package com.sis.controller;

import com.sis.dto.AcademicTermDTO;
import com.sis.dto.section.SectionDTO;
import com.sis.dto.section.SectionRequestDTO;
import com.sis.entities.AcademicTerm;
import com.sis.entities.Section;
import com.sis.entities.mapper.AcademicTermMapper;
import com.sis.entities.mapper.AcademicYearMapper;
import com.sis.service.AcademicTermService;
import com.sis.service.SectionService;
import com.sis.util.PageQueryUtil;
import com.sis.util.PageResult;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;


@RestController
@RequestMapping(value = "/api/sections")
@CrossOrigin(origins = ("*"))
@AllArgsConstructor
public class SectionController extends BaseController<Section, SectionDTO> {

    private final SectionService sectionService;

    private AcademicTermService academicTermService;

    private AcademicTermMapper academicTermMapper;


    @RequestMapping(value = "/search/{pageNumber}/{size}", method = RequestMethod.POST)
    public ResponseEntity<PageResult<SectionDTO>> search(@PathVariable int pageNumber,
                                                         @PathVariable int size,
                                                         @RequestBody SectionRequestDTO sectionRequestDTO) {
        PageQueryUtil pageUtil = new PageQueryUtil(pageNumber, size);
        return new ResponseEntity<>(sectionService.search(pageUtil, sectionRequestDTO), HttpStatus.OK);
    }

    @RequestMapping(value = "/getFacultyMemberSections/{facultyMemberId}", method = RequestMethod.GET)
    public ResponseEntity<ArrayList<SectionDTO>> getFacultyMemberSections(@PathVariable long facultyMemberId){
        AcademicTerm academicTerm = this.academicTermService.getCurrentAcademicTerm();
        AcademicTermDTO academicTermDTO = this.academicTermMapper.toDTO(academicTerm);

        ArrayList<SectionDTO> sectionDTOs=this.sectionService.findFacultyMemberSections(academicTermDTO.getYear_id(),academicTermDTO.getId(),facultyMemberId);
        return new ResponseEntity<>(sectionDTOs, HttpStatus.OK);
    }
}
