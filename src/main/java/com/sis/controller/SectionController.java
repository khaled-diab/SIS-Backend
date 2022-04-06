package com.sis.controller;

import com.sis.dto.AcademicTermDTO;
import com.sis.dto.section.SectionDTO;
import com.sis.dto.section.SectionRequestDTO;
import com.sis.entities.AcademicTerm;
import com.sis.entities.Section;
import com.sis.entities.Student;
import com.sis.entities.mapper.*;
import com.sis.exception.SectionFieldNotUniqueException;
import com.sis.service.AcademicTermService;
import com.sis.service.SectionService;
import com.sis.service.StudentService;
import com.sis.util.PageQueryUtil;
import com.sis.util.PageResult;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping(value = "/api/sections")
@CrossOrigin(origins = ("*"))
@AllArgsConstructor
@Validated
public class SectionController extends BaseController<Section, SectionDTO> {

    private final SectionService sectionService;
    private final SectionMapper sectionMapper;

    private AcademicTermService academicTermService;
    private AcademicTermMapper academicTermMapper;

//    private final StudentMapper studentMapper;
//    private final StudentService studentService;

    @RequestMapping(value = "/dataPage", method = RequestMethod.POST)
    public PageResult<SectionDTO> DataPage(@RequestBody PageQueryUtil pageUtil) {
        PageResult<SectionDTO> page = sectionMapper.toDataPage(this.sectionService.getDataPage(pageUtil));
        List<SectionDTO> sectionDTOS = page.getData();
        for (SectionDTO section : sectionDTOS) {
            int students = this.sectionService.countBySection(section.getId());
            section.setNumberOfStudents(students);
        }
        return page;
    }

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

//    @RequestMapping(value = "/getStudentSections/{studentId}", method = RequestMethod.GET)
//    public ResponseEntity<Collection<SectionDTO>> getStudentSections(@PathVariable long studentId) {
//        Student student = this.studentService.findById(studentId);
//        Collection<SectionDTO> sectionDTOs = this.sectionMapper.toDTOs(this.sectionService.getStudentSections(student.getId()));
//        return new ResponseEntity<>(sectionDTOs, HttpStatus.OK);
//    }

    // Abdo.Amr
    @RequestMapping(value = "/getFacultyMemberSections/{facultyMemberId}", method = RequestMethod.GET)
    public ResponseEntity<ArrayList<SectionDTO>> getFacultyMemberSections(@PathVariable long facultyMemberId) {
        AcademicTerm academicTerm = this.academicTermService.getCurrentAcademicTerm();
        AcademicTermDTO academicTermDTO = this.academicTermMapper.toDTO(academicTerm);

        ArrayList<SectionDTO> sectionDTOs = this.sectionService.findFacultyMemberSections(academicTermDTO.getAcademicYearDTO().getId(), academicTermDTO.getId(), facultyMemberId);
        return new ResponseEntity<>(sectionDTOs, HttpStatus.OK);
    }
}
