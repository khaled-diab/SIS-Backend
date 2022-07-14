package com.sis.controller;


import com.sis.dto.attendanceDetails.AttendanceBySection;
import com.sis.dto.attendanceDetails.AttendanceBySectionAndStudentDTO;
import com.sis.dto.attendanceDetails.AttendanceDetailsDTO;
import com.sis.dto.attendanceDetails.StudentLecture;
import com.sis.dto.attendanceReport.AttendanceReportDTO;
import com.sis.entity.AttendanceDetails;
import com.sis.entity.mapper.AttendanceDetailsMapper;
import com.sis.exception.ItemNotFoundException;
import com.sis.service.AttendanceDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/attendanceDetails")
@CrossOrigin("")
public class AttendanceDetailsController extends BaseController<AttendanceDetails, AttendanceDetailsDTO> {

    @Autowired
    private AttendanceDetailsService attendanceDetailsService;

    @Autowired
    private AttendanceDetailsMapper attendanceDetailsMapper;


    // by abdo ramadan
    @RequestMapping(value = "/updateReport", method = RequestMethod.POST)
    public ResponseEntity<AttendanceDetailsDTO> update(@RequestBody AttendanceDetailsDTO attendanceDetailsDTO) {
        AttendanceDetails attendanceDetails = attendanceDetailsMapper.toEntity(attendanceDetailsDTO);
        if (attendanceDetails != null) {
            attendanceDetailsService.save(attendanceDetails);
            return new ResponseEntity<>(attendanceDetailsDTO, HttpStatus.OK);
        } else {
            throw new ItemNotFoundException(attendanceDetailsDTO.getId());
        }
    }
    // by abdo ramadan
    @RequestMapping(value = "/updateStatusByStudentId", method = RequestMethod.POST)
    public ResponseEntity<String> updateStatusByStudentId(
            @RequestBody AttendanceBySectionAndStudentDTO attendanceBySectionAndStudentDTO, Long id ) {
        AttendanceDetailsDTO attendanceDetailsDTO1
                = attendanceDetailsMapper.toDTO(attendanceDetailsService.findById(id));
        if(attendanceDetailsDTO1!=null){
            attendanceDetailsDTO1.setAttendanceStatus(attendanceBySectionAndStudentDTO.getAttendanceStatus());
            attendanceDetailsService.save(attendanceDetailsMapper.toEntity(attendanceDetailsDTO1));
            return new ResponseEntity<>("Done", HttpStatus.OK);
        }else{
            throw new ItemNotFoundException(id);
        }
    }
    @RequestMapping(value = "/addAutoAttendance/{attendanceCode}", method = RequestMethod.POST)
    public ResponseEntity<AttendanceDetailsDTO> addAutoAttendance(@PathVariable long attendanceCode, @RequestBody StudentLecture studentLecture) {

        AttendanceDetailsDTO attendanceDetailsDTO = this.attendanceDetailsService.addAutoAttendance(attendanceCode,studentLecture);
        return new ResponseEntity<>(attendanceDetailsDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/addManualAttendance", method = RequestMethod.POST)
    public ResponseEntity<Collection<AttendanceDetailsDTO>> addManualAttendance(@RequestBody ArrayList<AttendanceDetailsDTO> attendanceDetailsDTOs) {

        List<AttendanceDetails> returnedAttendanceDetails = this.attendanceDetailsService.saveAll(this.attendanceDetailsMapper.toEntities(attendanceDetailsDTOs));

        return new ResponseEntity<>(this.attendanceDetailsMapper.toDTOs(returnedAttendanceDetails), HttpStatus.OK);
    }

    @RequestMapping(value = "/getAttendance/{studentId}/{courseId}", method = RequestMethod.GET)
    public ResponseEntity<Collection<AttendanceDetailsDTO>> getAttendance(@PathVariable long studentId, @PathVariable long courseId) {

        ArrayList<AttendanceDetailsDTO> attendanceDetailsDTOS = this.attendanceDetailsService.findStudentAttendances(studentId, courseId);
        return new ResponseEntity<>(attendanceDetailsDTOS, HttpStatus.OK);
    }

    @RequestMapping(value = "/getAttendancesByLecture/{lectureId}", method = RequestMethod.GET)
    public ResponseEntity<Collection<AttendanceDetailsDTO>> getAttendancesByLecture(@PathVariable long lectureId) {
        ArrayList<AttendanceDetailsDTO> attendanceDetailsDTOS = this.attendanceDetailsService.getAttendanceDetailsByLecture(lectureId);
        return new ResponseEntity<>(attendanceDetailsDTOS, HttpStatus.OK);
    }
//    // this function is written by abdo ramadan
//    @RequestMapping(value="/getAttendancesByLectureId/{lectureId}", method = RequestMethod.GET)
//    public ResponseEntity<Collection<AttendanceDetailsDTO>>
//    getAttendancesByLecture( @PathVariable Long lectureId){
//        Lecture lecture = lectureService.findById(lectureId);
//        ArrayList<AttendanceDetailsDTO> attendanceDetailsDTOS =
//                this.attendanceDetailsService.getAttendanceDetailsByLecture(lecture);
//        return new ResponseEntity<>(attendanceDetailsDTOS,HttpStatus.OK);
//    }

    // this function is written by Abdo Ramadan
    @RequestMapping(value = "/getAttendancesByLectureId/{lectureId}"
            , method = RequestMethod.GET)
    public ResponseEntity<AttendanceReportDTO>
    getAttendanceByLecture(@PathVariable long lectureId) {
        AttendanceReportDTO attendanceReportDTOS =
                this.attendanceDetailsService.findAttendanceReportDTOByLecture(lectureId);
        return new ResponseEntity<>(attendanceReportDTOS, HttpStatus.OK);
    }
    // Abdo Ramadan
    @RequestMapping(value = "/getAttendancesBySectionIdAndStudentId/{sectionId}/{studentId}",
            method = RequestMethod.GET)
    public ResponseEntity<Collection<AttendanceBySectionAndStudentDTO>> getAttendanceDetailsBySectoinAndStudentId
            (@PathVariable long sectionId ,@PathVariable long studentId) {
        ArrayList<AttendanceBySectionAndStudentDTO> attendanceDetailsDTOS =
                this.attendanceDetailsService.getAttendanceDetailsBySectoinAndStudentId(sectionId, studentId);
        return new ResponseEntity<>(attendanceDetailsDTOS, HttpStatus.OK);
    }

    @RequestMapping(value = "/getAttendancesBySectionId/{sectionId}",
            method = RequestMethod.GET)
    public ResponseEntity<Collection<AttendanceBySection>> getAttendanceDetailsBySectoin
            (@PathVariable long sectionId ) {
        ArrayList<AttendanceBySection> attendanceDetailsDTOS =
                this.attendanceDetailsService.getAttendanceDetailsBySectoin(sectionId);
        return new ResponseEntity<>(attendanceDetailsDTOS, HttpStatus.OK);
    }
}
