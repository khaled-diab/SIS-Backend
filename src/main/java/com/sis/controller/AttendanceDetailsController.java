package com.sis.controller;

import com.sis.dto.AcademicTermDTO;
import com.sis.dto.attendanceDetails.AttendanceDetailsDTO;

import com.sis.dto.attendanceDetails.StudentLecture;
import com.sis.dto.lecture.LectureDTO;
import com.sis.dto.section.SectionDTO;
import com.sis.dto.student.StudentDTO;
import com.sis.entities.AcademicTerm;
import com.sis.entities.AttendanceDetails;
import com.sis.entities.Lecture;
import com.sis.entities.Section;
import com.sis.entities.mapper.*;
import com.sis.service.*;
import com.sis.util.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalTime;
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

    @RequestMapping(value="/addAutoAttendance/{attendanceCode}", method = RequestMethod.POST)
    public ResponseEntity<AttendanceDetailsDTO> addAutoAttendance(@PathVariable long attendanceCode , @RequestBody StudentLecture studentLecture){

        StudentDTO studentDTO =studentLecture.getStudentDTO();
        LectureDTO lectureDTO =studentLecture.getLectureDTO();
        Lecture lecture = this.lectureMapper.toEntity(lectureDTO);
        AttendanceDetailsDTO attendanceDetailsDTO2=null;
        ArrayList<AttendanceDetailsDTO> attendanceDetailsDTOS = this.attendanceDetailsService.getAttendanceDetailsByLecture(lecture);
        for(AttendanceDetailsDTO attendanceDetailsDTO :attendanceDetailsDTOS){
            if(attendanceDetailsDTO.getStudentDTO().getId() == studentDTO.getId()){
                if ((lectureDTO.getAttendanceCode() == attendanceCode) && (lectureDTO.getAttendanceStatus())) {
                    attendanceDetailsDTO.setAttendanceStatus("Present");
                    this.attendanceDetailsService.save(this.attendanceDetailsMapper.toEntity(attendanceDetailsDTO));
                    attendanceDetailsDTO2= attendanceDetailsDTO;
                    break;
                }
            }
        }
//        System.out.println(lectureDTO.getAttendanceCode());
//        System.out.println(attendanceCode);
//        System.out.println(lectureDTO.getId());
//
//        AttendanceDetailsDTO attendanceDetailsDTO = AttendanceDetailsDTO.builder()
//                    .studentDTO(studentDTO)
//                    .lectureDTO(lectureDTO)
//                    .attendanceStatus(false)
//                    .attendanceDate(lectureDTO.getLectureDate())
//                    .lectureStartTime(lectureDTO.getLectureStartTime())
//                    .lectureEndTime(lectureDTO.getLectureEndTime())
//                    .courseDTO(lectureDTO.getCourseDTO())
//                    .build();
//        System.out.println(lectureDTO.getAttendanceCode());
//        System.out.println(attendanceCode);
//        System.out.println(lectureDTO.getAttendanceStatus());


          //  this.attendanceDetailsService.save(this.attendanceDetailsMapper.toEntity(attendanceDetailsDTO));

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
    @RequestMapping(value="/getAttendancesByLecture", method = RequestMethod.POST)
    public ResponseEntity<Collection<AttendanceDetailsDTO>> getAttendancesByLecture( @RequestBody Lecture lecture){

        ArrayList<AttendanceDetailsDTO> attendanceDetailsDTOS = this.attendanceDetailsService.getAttendanceDetailsByLecture(lecture);
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
