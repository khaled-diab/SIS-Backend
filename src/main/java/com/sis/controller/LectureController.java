package com.sis.controller;

import com.sis.dto.AcademicTermDTO;
import com.sis.dto.course.CourseDTO;
import com.sis.dto.lecture.LectureDTO;

import com.sis.dto.student.StudentDTO;
import com.sis.entities.*;

import com.sis.entities.mapper.*;

import com.sis.service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.*;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/lectures")
@CrossOrigin
public class LectureController extends BaseController<Lecture, LectureDTO>{

    @Autowired
    private LectureService lectureService;

    @Autowired
    private LectureMapper lectureMapper;

    @Autowired
    private SectionService sectionService;

    @Autowired
    private AcademicTermService academicTermService;

    @Autowired
    private AcademicTermMapper academicTermMapper;

    @Autowired
    private AcademicYearMapper academicYearMapper;

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private AttendanceDetailsService attendanceDetailsService;
    @Autowired
    private CourseMapper courseMapper;
    @Autowired
    private FacultyMemberMapper facultyMemberMapper;

    @RequestMapping(value="/addLecture", method = RequestMethod.POST)
public ResponseEntity<LectureDTO> addLecture( @RequestBody LectureDTO lectureDTO) {

        Course course = this.courseMapper.toEntity(lectureDTO.getCourseDTO());
        FacultyMember facultyMember = this.facultyMemberMapper.toEntity(lectureDTO.getFacultyMemberDTO());
        AcademicTerm academicTerm = this.academicTermService.getCurrentAcademicTerm();
        AcademicTermDTO academicTermDTO = this.academicTermMapper.toDTO(academicTerm);

        lectureDTO.setAcademicTermDTO(academicTermDTO);
        lectureDTO.setAcademicYearDTO(this.academicYearMapper.toDTO(academicTerm.getAcademicYear()));

        if(!lectureDTO.getAttendanceType() .equalsIgnoreCase("Manual")){
            Random rand = new Random();
            lectureDTO.setAttendanceCode(rand.nextInt());
        }
        boolean isFound=true;
            LectureDTO lectureDTO1 =this.lectureService.searchLecture(lectureDTO.getLectureDate(),course,facultyMember,lectureDTO.getLectureStartTime(),lectureDTO.getLectureEndTime());

            if(lectureDTO1==null){
                lectureDTO1=lectureDTO;
                System.out.println("nullll");
                isFound = false;
            }else {
                System.out.println("foundd");
                return new ResponseEntity<>(lectureDTO1, HttpStatus.OK);
            }

            Lecture lecture = this.lectureMapper.toEntity(lectureDTO1);
            LectureDTO lectureDTO2 = this.lectureMapper.toDTO(this.lectureService.save(lecture));

                this.attendanceDetailsService.saveAttendances(lectureDTO2);

    return new ResponseEntity<>(lectureDTO2, HttpStatus.OK);
}

    @RequestMapping(value="/getCurrentLecture", method = RequestMethod.POST)
    public ResponseEntity<Collection<LectureDTO>> getCurrentLectures( @RequestBody StudentDTO studentDTO) {

        AcademicTerm academicTerm = this.academicTermService.getCurrentAcademicTerm();
        Student student= this.studentMapper.toEntity(studentDTO);
//        AcademicTermDTO academicTermDTO = this.academicTermMapper.toDTO(academicTerm);
        Collection<Section> sections = this.sectionService.findStudentSections(academicTerm.getAcademicYear(),academicTerm,student);
        Collection<LectureDTO> lectureDTOs = new ArrayList<>();
        for(Section sec: sections){
             lectureDTOs.addAll(this.lectureMapper.toDTOs(sec.getLectures()));
        }
        lectureDTOs = lectureDTOs.stream().filter(lectureDTO -> lectureDTO.getAttendanceStatus() && lectureDTO.getAttendanceType().equalsIgnoreCase("Automatic")).collect(Collectors.toList());
        return new ResponseEntity<>(lectureDTOs, HttpStatus.OK);
    }

    @RequestMapping(value="/getFacultyMemberLectures/{sectionId}", method = RequestMethod.GET)
    public ResponseEntity<Collection<LectureDTO>> getFacultyMemberLectures(@PathVariable long sectionId) {

        AcademicTerm academicTerm = this.academicTermService.getCurrentAcademicTerm();
        AcademicTermDTO academicTermDTO = this.academicTermMapper.toDTO(academicTerm);
        ArrayList<LectureDTO> lectureDTOs = this.lectureService.getFacultyMemberLectures(academicTermDTO.getYear_id(),academicTermDTO.getId(),sectionId);
        return new ResponseEntity<>(lectureDTOs, HttpStatus.OK);
    }



}
