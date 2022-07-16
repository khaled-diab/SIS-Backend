package com.sis.controller;

import com.sis.dto.AcademicTermDTO;
import com.sis.dto.timetable.TimetableDTO;
import com.sis.dto.timetable.TimetableRequestDTO;
import com.sis.dto.timetable.TimetableTableRecordsDTO;
import com.sis.entity.AcademicTerm;
import com.sis.entity.Student;
import com.sis.entity.Timetable;
import com.sis.entity.mapper.AcademicTermMapper;
import com.sis.entity.mapper.TimetableMapper;
import com.sis.entity.security.User;
import com.sis.repository.StudentRepository;
import com.sis.service.AcademicTermService;
import com.sis.service.StudentService;
import com.sis.service.TimetableService;
import com.sis.service.UserService;
import com.sis.util.MessageResponse;
import com.sis.util.PageQueryUtil;
import com.sis.util.PageResult;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping(value = "/api/timetables")
@AllArgsConstructor
@Validated
public class TimetableController extends BaseController<Timetable, TimetableDTO> {

    private final TimetableService timetableService;
    private final TimetableMapper timetableMapper;
    private final StudentService studentService;
    private final StudentRepository studentRepository;
    private final UserService userService;

    //Abdo.Amr
    private AcademicTermService academicTermService;
    private AcademicTermMapper academicTermMapper;

    @RequestMapping(value = "/search/{pageNumber}/{size}", method = RequestMethod.POST)
    public ResponseEntity<PageResult<TimetableDTO>> search(@PathVariable int pageNumber,
                                                           @PathVariable int size,
                                                           @RequestBody TimetableRequestDTO timetableRequestDTO) {
        PageQueryUtil pageUtil = new PageQueryUtil(pageNumber, size);
        return new ResponseEntity<>(timetableService.search(pageUtil, timetableRequestDTO), HttpStatus.OK);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public MessageResponse update(@RequestBody @Valid TimetableDTO dto) {
        timetableService.save(timetableMapper.toEntity(dto));
        return new MessageResponse("Item has been updated successfully");
    }

    @RequestMapping(value = "/saveAll", method = RequestMethod.POST)
    public MessageResponse saveAll(@RequestBody @Valid List<TimetableDTO> dtos) {
        timetableService.saveAllTimetable(timetableMapper.toEntities(dtos));
        return new MessageResponse("Item has been updated successfully");
    }

    @RequestMapping(value = "/getStudentTimetables/{userId}", method = RequestMethod.GET)
    public ResponseEntity<Collection<TimetableTableRecordsDTO>> getStudentTimetables(@PathVariable long userId) {
        User user = this.userService.findById(userId);
        Student student = this.studentRepository.findStudentByUserId(user.getId());
//        Student student = this.studentService.findById(studentId);
        ArrayList<TimetableTableRecordsDTO> timetableDTOs = this.timetableService.getStudentTimetables(student.getId());
        return new ResponseEntity<>(timetableDTOs, HttpStatus.OK);
    }

    //Abdo.Amr
    @RequestMapping(
            value = "/facultyMemberTimeTables/{facultyMemberId}/{courseId}",
            method = RequestMethod.GET
    )
    public ResponseEntity<Collection<TimetableDTO>> getFacultyMemberTimeTables(@PathVariable long facultyMemberId,
                                                                               @PathVariable long courseId) {
        AcademicTerm academicTerm = this.academicTermService.getCurrentAcademicTerm();
        AcademicTermDTO academicTermDTO = this.academicTermMapper.toDTO(academicTerm);
        Collection<TimetableDTO> timetableDTO = this.timetableService.findFacultyMemberTimeTables(
                academicTermDTO.getAcademicYearDTO().getId(),
                academicTermDTO.getId(),
                facultyMemberId,
                courseId);
        return new ResponseEntity<>(timetableDTO, HttpStatus.OK);
    }

    //Abdo.Amr
    @RequestMapping(
            value = "/getSectionTimeTables/{sectionId}",
            method = RequestMethod.GET
    )
    public ResponseEntity<Collection<TimetableDTO>> getSectionTimeTables(@PathVariable long sectionId) {
        AcademicTerm academicTerm = this.academicTermService.getCurrentAcademicTerm();
        AcademicTermDTO academicTermDTO = this.academicTermMapper.toDTO(academicTerm);
        Collection<TimetableDTO> timetableDTOs = this.timetableService.getSectionTimeTables(
                academicTermDTO.getAcademicYearDTO().getId(),
                academicTermDTO.getId(),
                sectionId);
        return new ResponseEntity<>(timetableDTOs, HttpStatus.OK);
    }

    @RequestMapping(value = "/filter/{pageNumber}/{size}", method = RequestMethod.POST)
    public ResponseEntity<PageResult<TimetableTableRecordsDTO>> filter(@PathVariable int pageNumber,
                                                                       @PathVariable int size,
                                                                       @RequestBody TimetableRequestDTO timetableRequestDTO) {
        PageQueryUtil pageUtil = new PageQueryUtil(pageNumber, size);
        return new ResponseEntity<>(timetableService.filter(pageUtil, timetableRequestDTO), HttpStatus.OK);
    }

}
