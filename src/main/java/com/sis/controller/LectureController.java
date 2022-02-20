package com.sis.controller;

import com.sis.dto.course.CourseDTO;
import com.sis.dto.lecture.LectureDTO;
import com.sis.dto.student.StudentDTO;
import com.sis.dto.student.StudentFilterDTO;
import com.sis.dto.timetable.TimetableDTO;
import com.sis.entities.Lecture;
import com.sis.entities.Timetable;
import com.sis.entities.mapper.LectureMapper;
import com.sis.service.LectureService;
import com.sis.util.MessageResponse;
import com.sis.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Random;


@RestController
@RequestMapping("/api/lectures")
public class LectureController extends BaseController<Lecture, LectureDTO>{

    @Autowired
    private LectureService lectureService;

    @Autowired
    private LectureMapper lectureMapper;

    @RequestMapping(
            value = "/courses/{academicYearId}/{academicTermId}/{facultyMemberId}",
            method = RequestMethod.GET
    )
    public ResponseEntity<Collection<CourseDTO>> getCourses(
            @PathVariable long academicYearId,
            @PathVariable long academicTermId,
            @PathVariable long facultyMemberId) {

            Collection<CourseDTO> courseDTOS = this.lectureService.findCourses(
                    academicYearId,
                    academicTermId,
                    facultyMemberId);
        return new ResponseEntity<>(courseDTOS, HttpStatus.OK);
    }


    @RequestMapping(
            value = "/timeTables/{academicYearId}/{academicTermId}/{facultyMemberId}/{courseId}",
            method = RequestMethod.GET
    )
    public ResponseEntity<Collection<TimetableDTO>> getTimeTables(
            @PathVariable long academicYearId,
            @PathVariable long academicTermId,
            @PathVariable long facultyMemberId,
            @PathVariable long courseId) {


        Collection<TimetableDTO> timetableDTO = this.lectureService.findTimeTables(
                academicYearId,
                academicTermId,
                facultyMemberId,
                courseId);
        return new ResponseEntity<>(timetableDTO, HttpStatus.OK);
    }


@RequestMapping(value="/addLecture", method = RequestMethod.POST)
public ResponseEntity<LectureDTO> addLecture(@RequestBody LectureDTO lectureDTO) {
        if(lectureDTO.getAttendanceType() .equalsIgnoreCase("Manual")){
            this.lectureService.save(this.lectureMapper.toEntity(lectureDTO));
        }else {
            Random rand = new Random();
            lectureDTO.setAttendanceCode(rand.nextLong());
            this.lectureService.save(this.lectureMapper.toEntity(lectureDTO));
        }
    return new ResponseEntity<>(lectureDTO, HttpStatus.OK);
}

}
