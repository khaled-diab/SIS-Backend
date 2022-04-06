package com.sis.controller;


import com.sis.dto.attendanceDetails.AttendanceDetailsDTO;

import com.sis.dto.attendanceDetails.StudentLecture;
import com.sis.dto.attendanceReport.AttendanceReportDTO;
import com.sis.dto.lecture.LectureDTO;
import com.sis.entities.AttendanceDetails;
import com.sis.entities.Lecture;
import com.sis.entities.mapper.*;
import com.sis.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/attendanceDetails")
public class AttendanceDetailsController extends BaseController<AttendanceDetails, AttendanceDetailsDTO> {

    @Autowired
    private AttendanceDetailsService attendanceDetailsService;

    @Autowired
    private AttendanceDetailsMapper attendanceDetailsMapper;


    @Autowired
    private LectureMapper lectureMapper;


    @RequestMapping(value="/addAutoAttendance/{attendanceCode}", method = RequestMethod.POST)
    public ResponseEntity<AttendanceDetailsDTO> addAutoAttendance(@PathVariable long attendanceCode , @RequestBody StudentLecture studentLecture){

        long studentId =studentLecture.getStudentId();
        LectureDTO lectureDTO =studentLecture.getLectureDTO();
        Lecture lecture = this.lectureMapper.toEntity(lectureDTO);
        AttendanceDetailsDTO attendanceDetailsDTO2=null;
        ArrayList<AttendanceDetailsDTO> attendanceDetailsDTOS = this.attendanceDetailsService.getAttendanceDetailsByLecture(lecture.getId());
        for(AttendanceDetailsDTO attendanceDetailsDTO :attendanceDetailsDTOS){
            if(attendanceDetailsDTO.getStudentDTO().getId() == studentId){
                if ((lectureDTO.getAttendanceCode() == attendanceCode) && (lectureDTO.getAttendanceStatus())) {
                    attendanceDetailsDTO.setAttendanceStatus("Present");
                    this.attendanceDetailsService.save(this.attendanceDetailsMapper.toEntity(attendanceDetailsDTO));
                    attendanceDetailsDTO2= attendanceDetailsDTO;
                    break;
                }
            }
        }
        return new ResponseEntity<>(attendanceDetailsDTO2,HttpStatus.OK);
    }
    @RequestMapping(value="/addManualAttendance", method = RequestMethod.POST)
    public ResponseEntity<Collection<AttendanceDetailsDTO>> addManualAttendance(@RequestBody ArrayList<AttendanceDetailsDTO> attendanceDetailsDTOs){

        List<AttendanceDetails> returnedAttendanceDetails =  this.attendanceDetailsService.saveAll(this.attendanceDetailsMapper.toEntities(attendanceDetailsDTOs));

        return new ResponseEntity<>(this.attendanceDetailsMapper.toDTOs(returnedAttendanceDetails),HttpStatus.OK);
    }

    @RequestMapping(value="/getAttendance/{studentId}/{courseId}", method = RequestMethod.GET)
    public ResponseEntity<Collection<AttendanceDetailsDTO>> getAttendance( @PathVariable long studentId, @PathVariable long courseId){

        ArrayList<AttendanceDetailsDTO> attendanceDetailsDTOS = this.attendanceDetailsService.findStudentAttendances(studentId,courseId);
        return new ResponseEntity<>(attendanceDetailsDTOS,HttpStatus.OK);
    }
    @RequestMapping(value="/getAttendancesByLecture/{lectureId}", method = RequestMethod.GET)
    public ResponseEntity<Collection<AttendanceDetailsDTO>> getAttendancesByLecture( @PathVariable long lectureId){
        ArrayList<AttendanceDetailsDTO> attendanceDetailsDTOS = this.attendanceDetailsService.getAttendanceDetailsByLecture(lectureId);
        return new ResponseEntity<>(attendanceDetailsDTOS,HttpStatus.OK);
    }

    // this function is written by Abdo Ramadan
    @RequestMapping(value="/getAttendanceByLecture/{lectureId}"
            , method = RequestMethod.GET)
    public ResponseEntity<AttendanceReportDTO>
    getAttendanceByLecture( @PathVariable long lectureId){
        AttendanceReportDTO attendanceReportDTOS =
                this.attendanceDetailsService.findAttendanceReportDTOByLecture(lectureId);
        return new ResponseEntity<>(attendanceReportDTOS,HttpStatus.OK);
    }

}
