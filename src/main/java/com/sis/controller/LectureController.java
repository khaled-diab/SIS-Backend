package com.sis.controller;

import com.sis.dto.AcademicTermDTO;
import com.sis.dto.attendanceReport.FacultyMemberLecturesDTO;
import com.sis.dto.lecture.LectureDTO;
import com.sis.entity.AcademicTerm;
import com.sis.entity.Lecture;
import com.sis.entity.Section;
import com.sis.entity.mapper.AcademicTermMapper;
import com.sis.entity.mapper.LectureMapper;
import com.sis.service.AcademicTermService;
import com.sis.service.LectureService;
import com.sis.service.SectionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/lectures")
@AllArgsConstructor
public class LectureController extends BaseController<Lecture, LectureDTO> {

    private LectureService lectureService;

    private LectureMapper lectureMapper;

    private SectionService sectionService;

    private AcademicTermService academicTermService;

    private AcademicTermMapper academicTermMapper;


    @RequestMapping(value = "/addLecture", method = RequestMethod.POST)
    public ResponseEntity<LectureDTO> addLecture(@Validated @RequestBody LectureDTO lectureDTO) {

        return new ResponseEntity<>(this.lectureService.addLecture(lectureDTO), HttpStatus.OK);
    }

    @RequestMapping(value = "/getCurrentLecture/{studentId}", method = RequestMethod.GET)
    public ResponseEntity<Collection<Long>> getCurrentLectures(@PathVariable long studentId) {

        AcademicTerm academicTerm = this.academicTermService.getCurrentAcademicTerm();
        Collection<Section> sections = this.sectionService.findStudentSections(academicTerm.getAcademicYear(), academicTerm, studentId);
        Collection<Long> lectureIds = new ArrayList<>();
        for (Section sec : sections) {
//            lectureDTOs.addAll(this.lectureMapper.toDTOs(sec.getLectures()));
            for(Lecture lecture : sec.getLectures()){
                if(lecture.getAttendanceStatus() && lecture.getAttendanceType().equalsIgnoreCase("Automatic")){
                    lectureIds.add(lecture.getId());
                }
            }
        }
//       Collection<Long> ids = lectureDTOs.stream().filter(lectureDTO -> lectureDTO.getAttendanceStatus() && lectureDTO.getAttendanceType().equalsIgnoreCase("Automatic")).collect(Collectors.toList());
        return new ResponseEntity<>(lectureIds, HttpStatus.OK);
    }


    @RequestMapping(value = "/getFacultyMemberLectures/{sectionId}", method = RequestMethod.GET)
    public ResponseEntity<Collection<LectureDTO>> getFacultyMemberLectures(@PathVariable long sectionId) {
        AcademicTerm academicTerm = this.academicTermService.getCurrentAcademicTerm();
        AcademicTermDTO academicTermDTO = this.academicTermMapper.toDTO(academicTerm);
        ArrayList<LectureDTO> lectureDTOs = this.lectureService.
                getFacultyMemberLectures(academicTermDTO.getAcademicYearDTO().getId(), academicTermDTO.getId(), sectionId);
        return new ResponseEntity<>(lectureDTOs, HttpStatus.OK);
    }

    // this function is written by Abdo Ramadan
    @RequestMapping(value = "/getFacultyMemberLecturesToReport/{sectionId}",
            method = RequestMethod.GET)
    public ResponseEntity<Collection<FacultyMemberLecturesDTO>> getFacultyMemberLecturesToReport(
            @PathVariable long sectionId) {
        AcademicTerm academicTerm = this.academicTermService.getCurrentAcademicTerm();
        AcademicTermDTO academicTermDTO = this.academicTermMapper.toDTO(academicTerm);
        ArrayList<FacultyMemberLecturesDTO> facultyMemberLecturesDTOS =
                this.lectureService.getFacultyMemberLecturesToReport(
                        academicTermDTO.getAcademicYearDTO().getId(), academicTermDTO.getId(), sectionId);
        return new ResponseEntity<>(facultyMemberLecturesDTOS, HttpStatus.OK);
    }

}
