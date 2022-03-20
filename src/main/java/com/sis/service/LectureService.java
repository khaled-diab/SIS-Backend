package com.sis.service;

import com.sis.dao.LectureRepository;
import com.sis.dto.attendanceReport.FacultyMemberLecturesDTO;
import com.sis.dto.lecture.LectureDTO;

import com.sis.entities.*;
import com.sis.entities.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


@Service
public class LectureService  extends BaseServiceImp<Lecture>{


    @Autowired
    private LectureRepository lectureRepository;

    @Autowired
    private LectureMapper lectureMapper;

    @Override
    public JpaRepository<Lecture, Long> Repository() {
        return this.lectureRepository;
    }

    public ArrayList<LectureDTO> getFacultyMemberLectures(long academicYearId,
                                                          long academicTermId,
                                                          long sectionId){

        ArrayList<Long> lectureIds = lectureRepository.findFacultyMemberLectures(sectionId);
        ArrayList<LectureDTO> LectureDTOs = new ArrayList<>();
        for(Long id : lectureIds){
            Lecture lecture = findById(id);
            if(lecture.getAcademicTermId().getId() ==
                    academicTermId && lecture.getAcademicYearId().getId() == academicYearId) {
                LectureDTOs.add(this.lectureMapper.toDTO(lecture));
            }
        }
        return LectureDTOs;
    }
    //this function is written by Abdo Ramadan
    public ArrayList<FacultyMemberLecturesDTO> getFacultyMemberLecturesToReport(long academicYearId,
                                                                        long academicTermId,
                                                                        long sectionId){
        ArrayList<Long> lectureIds = lectureRepository.findFacultyMemberLectures(sectionId);
        ArrayList<FacultyMemberLecturesDTO> facultyMemberLecturesDTOS = new ArrayList<>();
        for(Long id : lectureIds){
            Lecture lecture = findById(id);
            if(lecture.getAcademicTermId().getId() ==
                    academicTermId && lecture.getAcademicYearId().getId() == academicYearId) {
                FacultyMemberLecturesDTO facultyMemberLecturesDTO  =new FacultyMemberLecturesDTO();
                facultyMemberLecturesDTO.setLectureEndTime(lecture.getLectureEndTime());
                facultyMemberLecturesDTO.setLectureDay(lecture.getLectureDay());
                facultyMemberLecturesDTO.setLectureStartTime(lecture.getLectureStartTime());
                facultyMemberLecturesDTO.setLectureDate(lecture.getLectureDate());
                facultyMemberLecturesDTO.setId(lecture.getId());
                facultyMemberLecturesDTOS.add(facultyMemberLecturesDTO);
            }
        }
        return facultyMemberLecturesDTOS;
    }

}
