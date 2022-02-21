package com.sis.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sis.dto.attendanceDetails.AttendanceDetailsDTO;
import com.sis.dto.lecture.LectureDTO;
import com.sis.dto.student.StudentDTO;
import com.sis.entities.AttendanceDetails;
import com.sis.entities.mapper.AttendanceDetailsMapper;
import com.sis.entities.mapper.LectureMapper;
import com.sis.entities.mapper.StudentMapper;
import com.sis.service.AttendanceDetailsService;
import com.sis.service.LectureService;
import com.sis.service.StudentService;
import com.sis.util.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Random;

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

    @RequestMapping(value="/addAttendance/{attendanceCode}/{studentId}/{lectureId}", method = RequestMethod.GET)
    public ResponseEntity<AttendanceDetailsDTO> addAttendance(@PathVariable long attendanceCode , @PathVariable long studentId,
                                                              @PathVariable long lectureId){

        LectureDTO lectureDTO = this .lectureMapper.toDTO(this.lectureService.findById(lectureId));
        StudentDTO studentDTO = this .studentMapper.toDTO(this.studentService.findById(studentId));
        LocalTime now = LocalTime.now();
        AttendanceDetailsDTO attendanceDetailsDTO = AttendanceDetailsDTO.builder()
                .studentDTO(studentDTO)
                .lectureDTO(lectureDTO)
                .attendanceStatus("Absent")
                .attendanceDate(lectureDTO.getLectureDate())
                .lectureStartTime(lectureDTO.getLectureStartTime())
                .lectureEndTime(lectureDTO.getLectureEndTime())
                .build();

        System.out.println(attendanceCode);
        System.out.println(lectureDTO.getAttendanceCode());
        System.out.println(lectureDTO.getAttendanceCodeExpiringTime());
        System.out.println(now);

        if( (lectureDTO.getAttendanceCode() == attendanceCode) && (lectureDTO.getAttendanceCodeExpiringTime().isAfter(now)) ){
                attendanceDetailsDTO.setAttendanceStatus("Present");
        }
        this.attendanceDetailsService.save(this.attendanceDetailsMapper.toEntity(attendanceDetailsDTO));
        return new ResponseEntity<>(attendanceDetailsDTO,HttpStatus.OK);
    }


}
