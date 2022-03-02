package com.sis.controller;

import com.sis.dto.AcademicTermDTO;
import com.sis.dto.lecture.LectureDTO;

import com.sis.entities.AcademicTerm;
import com.sis.entities.Lecture;
import com.sis.entities.Section;

import com.sis.entities.mapper.AcademicTermMapper;
import com.sis.entities.mapper.LectureMapper;

import com.sis.service.AcademicTermService;
import com.sis.service.LectureService;
import com.sis.service.SectionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/lectures")
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

@RequestMapping(value="/addLecture", method = RequestMethod.POST)
public ResponseEntity<LectureDTO> addLecture( @RequestBody LectureDTO lectureDTO) {


        Lecture lecture = this.lectureMapper.toEntity(lectureDTO);
        if(lectureDTO.getAttendanceType() .equalsIgnoreCase("Manual")){
        }else {
            Random rand = new Random();
            lecture.setAttendanceCode(rand.nextLong());

        }
         this.lectureService.save(lecture);

    return new ResponseEntity<>(lectureDTO, HttpStatus.OK);
}

    @RequestMapping(value="/getCurrentLecture/courseId/{studentId}", method = RequestMethod.GET)
    public ResponseEntity<Collection<LectureDTO>> getCurrentLectures( @PathVariable long studentId) {

        AcademicTerm academicTerm = this.academicTermService.getCurrentAcademicTerm();
        AcademicTermDTO academicTermDTO = this.academicTermMapper.toDTO(academicTerm);
        LocalTime now = LocalTime.now();
        String today = LocalDate.now().toString();
        String todays=today.replace('-','/');
        Collection<Section> sections = this.sectionService.findStudentSections(academicTermDTO.getYear_id(),academicTermDTO.getId(),studentId);
        Collection<LectureDTO> lectureDTOs = new ArrayList<>();
        for(Section sec: sections){
             lectureDTOs.addAll(this.lectureMapper.toDTOs(sec.getLectures()));
        }
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        lectureDTOs = lectureDTOs.stream().filter(lectureDTO -> lectureDTO.getAttendanceCodeExpiringTime().isAfter(now) && todays.equals( dateFormat.format(lectureDTO.getLectureDate())) && lectureDTO.getAttendanceType().equalsIgnoreCase("Auto")).collect(Collectors.toList());
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
