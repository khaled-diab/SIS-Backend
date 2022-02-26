package com.sis.service;

import com.sis.dao.LectureRepository;
import com.sis.dto.course.CourseDTO;
import com.sis.dto.lecture.LectureDTO;
import com.sis.dto.section.SectionDTO;
import com.sis.dto.studentEnrollment.StudentEnrollmentDTO;
import com.sis.dto.timetable.TimetableDTO;
import com.sis.entities.*;
import com.sis.entities.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class LectureService  extends BaseServiceImp<Lecture>{


    @Autowired
    private LectureRepository lectureRepository;

    @Autowired
    private FacultyMemberEnrollmentService facultyMemberEnrollmentService;



    @Autowired
    private LectureMapper lectureMapper;

    @Override
    public JpaRepository<Lecture, Long> Repository() {
        return this.lectureRepository;
    }

    public ArrayList<LectureDTO> getFacultyMemberLectures(long sectionId){

        ArrayList<Long> lectureIds = lectureRepository.findFacultyMemberLectures(sectionId);
        ArrayList<LectureDTO> LectureDTOs = new ArrayList<>();
        for(Long id : lectureIds){
            Lecture lecture = findById(id);
            LectureDTOs.add(this.lectureMapper.toDTO(lecture));
        }
        return LectureDTOs;
    }
}
