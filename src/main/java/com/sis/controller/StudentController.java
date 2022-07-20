package com.sis.controller;

import com.sis.dto.AcademicTermDTO;
import com.sis.dto.section.SectionDTO;
import com.sis.dto.student.StudentDTO;
import com.sis.dto.student.StudentFilterDTO;
import com.sis.dto.student.StudentRecordDTO;
import com.sis.entity.AcademicTerm;
import com.sis.entity.Student;
import com.sis.entity.mapper.AcademicTermMapper;
import com.sis.entity.mapper.StudentMapper;
import com.sis.entity.mapper.StudentRecordMapper;
import com.sis.exception.StudentFieldNotUniqueException;
import com.sis.service.AcademicTermService;
import com.sis.service.StudentService;
import com.sis.util.MessageResponse;
import com.sis.util.PageQueryUtil;
import com.sis.util.PageResult;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@Validated
@RequestMapping(value = "/api/students")
@AllArgsConstructor
public class StudentController extends BaseController<Student, StudentDTO> {


    //Autowired
    private final StudentService studentService;
    //Autowired
    private final StudentMapper studentMapper;


    @Autowired
    private AcademicTermService academicTermService;

    @Autowired
    private AcademicTermMapper academicTermMapper;
    @Autowired
    private StudentRecordMapper studentRecordMapper;

    @RequestMapping(value = "/addStudent", method = RequestMethod.POST)
    public MessageResponse createStudent(@Valid @RequestBody StudentDTO dto) {


        if (this.studentService.findByuniversityId(dto.getUniversityId()) != null) {

            throw new StudentFieldNotUniqueException("universityId", "University Id Already Exists");
        }
        if (this.studentService.findByNationalId(dto.getNationalId()) != null) {
            throw new StudentFieldNotUniqueException("nationalId", "NationalID Id Already Exists");
        }
        if (this.studentService.findByUniversityMail(dto.getUniversityMail()) != null) {
            throw new StudentFieldNotUniqueException("universityMail", "University Mail Already Exists");
        }
//        this.userService.save(this.userMapper.toEntity(dto.getUser()));
        this.studentService.save(this.studentMapper.toEntity(dto));
        return new MessageResponse("Item has been saved successfully");
    }

    @RequestMapping(value = "/updateStudent", method = RequestMethod.POST)
    public MessageResponse updateStudent(@Valid @RequestBody StudentDTO dto) {

        Student studentByuniversityID = this.studentService.findByuniversityId(dto.getUniversityId());
        Student studentByNationalID = this.studentService.findByNationalId(dto.getNationalId());
        Student studentByuniversityMail = this.studentService.findByUniversityMail(dto.getUniversityMail());
        if (studentByuniversityID != null && studentByuniversityID.getId() != dto.getId()) {
            throw new StudentFieldNotUniqueException("universityId", "University Id Already Exists");
        }
        if (studentByNationalID != null && studentByNationalID.getId() != dto.getId()) {
            throw new StudentFieldNotUniqueException("nationalId", "NationalID Id Already Exists");
        }
        if (studentByuniversityMail != null && studentByuniversityMail.getId() != dto.getId()) {
            throw new StudentFieldNotUniqueException("universityMail", "University Mail Already Exists");
        }
//        User user = this.userService.findById(dto.getUser().getId());

        this.studentService.save(this.studentMapper.toEntity(dto));
        return new MessageResponse("Item has been updated successfully");
    }

    @RequestMapping(value = "/deleteStudent/{id}", method = RequestMethod.GET)
    public MessageResponse delete(@PathVariable(value = "id") Long id) {

        this.studentService.deleteById(id);

        return new MessageResponse("Item has been deleted successfully");
    }

    @RequestMapping(
            value = "/searchStudent",
            method = RequestMethod.POST
    )
    public ResponseEntity<PageResult<StudentDTO>> searchStudentPage(
            @RequestParam int page, @RequestParam int limit,
            @RequestBody StudentFilterDTO filterDTO) {

        PageQueryUtil queryUtil = new PageQueryUtil(page, limit);
        PageResult<StudentDTO> result = this.studentService.search(queryUtil, filterDTO);
        return new ResponseEntity<>(result, HttpStatus.OK);

    }

    @RequestMapping(
            value = "/studentsBySection",
            method = RequestMethod.POST
    )
    public ArrayList<StudentDTO> getStudentsBySection(@RequestBody ArrayList<SectionDTO> sectionDTOs) {
        AcademicTerm academicTerm = this.academicTermService.getCurrentAcademicTerm();
        AcademicTermDTO academicTermDTO = this.academicTermMapper.toDTO(academicTerm);
        ArrayList<StudentDTO> studentDTOs = new ArrayList<>();
        for (SectionDTO sectionDTO : sectionDTOs) {
            studentDTOs.addAll(this.studentService.findStudentsBySection(academicTermDTO.getAcademicYearDTO().getId(), academicTermDTO.getId(), sectionDTO.getId()));
        }
        return studentDTOs;

    }

    @RequestMapping(
            value = "/searchRecords",
            method = RequestMethod.POST
    )
    public ResponseEntity<PageResult<StudentRecordDTO>> searchStudentPageRecords(
            @RequestParam int page, @RequestParam int limit,
            @RequestBody StudentFilterDTO filterDTO) {
        System.out.println(filterDTO);
        PageQueryUtil queryUtil = new PageQueryUtil(page, limit);
        PageResult<StudentDTO> studentPage = this.studentService.search(queryUtil, filterDTO);
        List<StudentRecordDTO> studentRecordDTOS = this.studentRecordMapper.dtosToDTOs(studentPage.getData());
        PageResult<StudentRecordDTO> recordPage = new PageResult<>(studentRecordDTOS, studentPage.getTotalCount(), studentPage.getPageSize(),
                studentPage.getCurrPage());

        return new ResponseEntity<>(recordPage, HttpStatus.OK);

    }
}
