package com.sis.controller;

import com.sis.dto.AcademicTermDTO;
import com.sis.dto.studentEnrollment.StudentEnrollmentRequestDTO;
import com.sis.dto.timetable.TimetableDTO;
import com.sis.dto.timetable.TimetableRequestDTO;
import com.sis.entities.AcademicTerm;
import com.sis.entities.Section;
import com.sis.entities.Timetable;
import com.sis.entities.mapper.AcademicTermMapper;
import com.sis.service.AcademicTermService;
import com.sis.service.StudentEnrollmentService;
import com.sis.service.TimetableService;
import com.sis.util.PageQueryUtil;
import com.sis.util.PageResult;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping(value = "/api/timetables")
@CrossOrigin(origins = ("*"))
@AllArgsConstructor
public class TimetableController extends BaseController<Timetable, TimetableDTO> {

    private final TimetableService timetableService;

    //UC011
    private StudentEnrollmentService studentEnrollmentService;

    //UC011
    private AcademicTermService academicTermService;

    //UC011
    private AcademicTermMapper academicTermMapper;

    @RequestMapping(value = "/filter/{pageNumber}/{size}", method = RequestMethod.POST)
    public ResponseEntity<PageResult<TimetableDTO>> filter(@PathVariable int pageNumber,
                                                           @PathVariable int size,
                                                           @RequestBody TimetableRequestDTO timetableRequestDTO) {
        PageQueryUtil pageUtil = new PageQueryUtil(pageNumber, size);
        if (timetableRequestDTO.getFilterStudent() != null) {
//            studentEnrollmentRequestDTO.setFilterStudent(timetableRequestDTO.getFilterStudent());
            Collection <Section> sections = studentEnrollmentService.findStudentSections(timetableRequestDTO.getFilterAcademicYear(),
                    timetableRequestDTO.getFilterAcademicTerm(), timetableRequestDTO.getFilterStudent());
//            for (int i = 0; i < ; i++) {
//
//            }
        }
        return new ResponseEntity<>(timetableService.filter(pageUtil, timetableRequestDTO), HttpStatus.OK);
    }

    //UC011
    @RequestMapping(
            value = "/facultyMemberTimeTables/{facultyMemberId}/{courseId}",
            method = RequestMethod.GET
    )
    public ResponseEntity<Collection<TimetableDTO>> getFacultyMemberTimeTables(@PathVariable long facultyMemberId,
                                                                               @PathVariable long courseId) {



        AcademicTerm academicTerm = this.academicTermService.getCurrentAcademicTerm();
        AcademicTermDTO academicTermDTO = this.academicTermMapper.toDTO(academicTerm);
        Collection<TimetableDTO> timetableDTO = this.timetableService.findFacultyMemberTimeTables(
                academicTermDTO.getYear_id(),
                academicTermDTO.getId(),
                facultyMemberId,
                courseId);
        return new ResponseEntity<>(timetableDTO, HttpStatus.OK);
    }
}
