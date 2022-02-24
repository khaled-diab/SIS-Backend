package com.sis.controller;

import com.sis.dto.studentEnrollment.StudentEnrollmentRequestDTO;
import com.sis.dto.timetable.TimetableDTO;
import com.sis.dto.timetable.TimetableRequestDTO;
import com.sis.entities.Section;
import com.sis.entities.Timetable;
import com.sis.service.StudentEnrollmentService;
import com.sis.service.TimetableService;
import com.sis.util.PageQueryUtil;
import com.sis.util.PageResult;
import lombok.AllArgsConstructor;
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
    private StudentEnrollmentRequestDTO studentEnrollmentRequestDTO;
    private StudentEnrollmentService studentEnrollmentService;

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
}
