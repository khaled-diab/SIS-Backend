package com.sis.controller;

import com.sis.dto.attendanceDetails.AttendanceDetailsDTO;
import com.sis.dto.lecture.LectureDTO;
import com.sis.dto.section.SectionDTO;
import com.sis.dto.student.StudentDTO;
import com.sis.entities.AttendanceDetails;
import com.sis.entities.Lecture;
import com.sis.entities.Section;
import com.sis.entities.mapper.AttendanceDetailsMapper;
import com.sis.entities.mapper.LectureMapper;
import com.sis.entities.mapper.SectionMapper;
import com.sis.entities.mapper.StudentMapper;
import com.sis.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;

@RestController
@RequestMapping("/api/attendanceDetails")
public class AttendanceDetailsController extends BaseController<AttendanceDetails, AttendanceDetailsDTO> {

    @Autowired
    private AttendanceDetailsService attendanceDetailsService;

    @Autowired
    private AttendanceDetailsMapper attendanceDetailsMapper;

    @Autowired
    private LectureService lectureService;

    @Autowired
    private LectureMapper lectureMapper;

    @Autowired
    private StudentService studentService;

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private SectionService sectionService;
    @Autowired
    private SectionMapper sectionMapper;

    @Autowired
    private StudentEnrollmentService studentEnrollmentService;

    @RequestMapping(value="/addAttendance/{attendanceCode}/{studentId}/{lectureId}/{sectionId}", method = RequestMethod.GET)
    public ResponseEntity<AttendanceDetailsDTO> addAttendance(@PathVariable long attendanceCode , @PathVariable long studentId,
                                                              @PathVariable long lectureId,   @PathVariable long sectionId){

        LectureDTO lectureDTO = this .lectureMapper.toDTO(this.lectureService.findById(lectureId));
        StudentDTO studentDTO = this .studentMapper.toDTO(this.studentService.findById(studentId));
        SectionDTO sectionDto = this .sectionMapper.toDTO(this.sectionService.findById(sectionId));
        LocalTime now = LocalTime.now();
        AttendanceDetailsDTO attendanceDetailsDTO = AttendanceDetailsDTO.builder()
                .studentDTO(studentDTO)
                .lectureDTO(lectureDTO)
                .attendanceStatus("Absent")
                .attendanceDate(lectureDTO.getLectureDate())
                .lectureStartTime(lectureDTO.getLectureStartTime())
                .lectureEndTime(lectureDTO.getLectureEndTime())
                .sectionDTO(sectionDto)
                .build();
        if( (lectureDTO.getAttendanceCode() == attendanceCode) && (lectureDTO.getAttendanceCodeExpiringTime().isAfter(now)) ){
                attendanceDetailsDTO.setAttendanceStatus("Present");
        }
        this.attendanceDetailsService.save(this.attendanceDetailsMapper.toEntity(attendanceDetailsDTO));
        return new ResponseEntity<>(attendanceDetailsDTO,HttpStatus.OK);
    }

    @RequestMapping(value="/getAttendance/{academicYearId}/{academicTermId}/{studentId}/{courseId}", method = RequestMethod.GET)
    public ResponseEntity<Collection<AttendanceDetailsDTO>> getAttendance(@PathVariable long academicYearId ,  @PathVariable long academicTermId,
                                                              @PathVariable long studentId, @PathVariable long courseId){

        Section section = this.studentEnrollmentService.findStudentSection(academicYearId,academicTermId,studentId,courseId);
        ArrayList<AttendanceDetailsDTO> attendanceDetailsDTOS = this.attendanceDetailsService.findStudentAttendances(studentId,section.getId());
        return new ResponseEntity<>(attendanceDetailsDTOS,HttpStatus.OK);
    }


}
