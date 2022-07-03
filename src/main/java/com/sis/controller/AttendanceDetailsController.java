package com.sis.controller;


import com.sis.dto.attendanceDetails.AttendanceDetailsDTO;
import com.sis.dto.attendanceDetails.StudentLecture;
import com.sis.dto.attendanceReport.AttendanceReportDTO;
import com.sis.dto.lecture.LectureDTO;
import com.sis.entity.AttendanceDetails;
import com.sis.entity.Lecture;
import com.sis.entity.mapper.AttendanceDetailsMapper;
import com.sis.entity.mapper.LectureMapper;
import com.sis.exception.ItemNotFoundException;
import com.sis.service.AttendanceDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/api/attendanceDetails")
public class AttendanceDetailsController extends BaseController<AttendanceDetails, AttendanceDetailsDTO> {

    @Autowired
    private AttendanceDetailsService attendanceDetailsService;

    @Autowired
    private AttendanceDetailsMapper attendanceDetailsMapper;


    @Autowired
    private LectureMapper lectureMapper;

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

    @RequestMapping(value = "/addAutoAttendance/{attendanceCode}", method = RequestMethod.POST)
    public ResponseEntity<AttendanceDetailsDTO> addAutoAttendance(@PathVariable long attendanceCode, @RequestBody StudentLecture studentLecture) {

        long studentId = studentLecture.getStudentId();
        LectureDTO lectureDTO = studentLecture.getLectureDTO();
        Lecture lecture = this.lectureMapper.toEntity(lectureDTO);
        AttendanceDetailsDTO attendanceDetailsDTO2 = null;
        ArrayList<AttendanceDetailsDTO> attendanceDetailsDTOS = this.attendanceDetailsService.getAttendanceDetailsByLecture(lecture.getId());
        for (AttendanceDetailsDTO attendanceDetailsDTO : attendanceDetailsDTOS) {
            if (attendanceDetailsDTO.getStudentDTO().getId() == studentId) {
                if ((lectureDTO.getAttendanceCode() == attendanceCode) && (lectureDTO.getAttendanceStatus())) {
                    attendanceDetailsDTO.setAttendanceStatus("Present");
                    this.attendanceDetailsService.save(this.attendanceDetailsMapper.toEntity(attendanceDetailsDTO));
                    attendanceDetailsDTO2 = attendanceDetailsDTO;
                    break;
                }
            }
        }
        return new ResponseEntity<>(attendanceDetailsDTO2, HttpStatus.OK);
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
    @RequestMapping(value = "/getAttendancesBySectionIdAndStudentId/{sectionId}/{studentId}",
            method = RequestMethod.GET)
    public ResponseEntity<Collection<AttendanceDetailsDTO>> getAttendanceDetailsBySectoinAndStudentId
            (@PathVariable long sectionId ,@PathVariable long studentId) {
        ArrayList<AttendanceDetailsDTO> attendanceDetailsDTOS =
                this.attendanceDetailsService.getAttendanceDetailsBySectoinAndStudentId(sectionId, studentId);
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

}
