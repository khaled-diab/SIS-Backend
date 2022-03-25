package com.sis.service;

import com.sis.dto.attendanceReport.FacultyMemberLecturesDTO;
import com.sis.dto.lecture.LectureDTO;
import com.sis.entities.Course;
import com.sis.entities.FacultyMember;
import com.sis.entities.Lecture;
import com.sis.entities.mapper.LectureMapper;
import com.sis.repository.LectureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;


@Service
public class LectureService  extends BaseServiceImp<Lecture> {


    @Autowired
    private LectureRepository lectureRepository;

    @Autowired
    private LectureMapper lectureMapper;

    @Override
    public JpaRepository<Lecture, Long> Repository() {
        return this.lectureRepository;
    }

    public ArrayList<LectureDTO> getFacultyMemberLectures(long academicYearId, long academicTermId, long sectionId) {

        ArrayList<Long> lectureIds = lectureRepository.findFacultyMemberLectures(sectionId);
        ArrayList<LectureDTO> LectureDTOs = new ArrayList<>();
        for (Long id : lectureIds) {
            Lecture lecture = findById(id);
            if (lecture.getAcademicTermId().getId() == academicTermId && lecture.getAcademicYearId().getId() == academicYearId) {
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

    public LectureDTO searchLecture(Date lectureDate, Course course, FacultyMember facultyMember, LocalTime lectureStartTime, LocalTime lectureEndTime)
    {

            ArrayList<Lecture> lectures = this.lectureRepository.findLectureByLectureDateAndCourseIdAndFacultyMemberIdAndLectureStartTimeAndLectureEndTime(lectureDate,
                    course,
                    facultyMember,
                    lectureStartTime,
                    lectureEndTime);
            if (lectures!=null && lectures.size()>0) {
                return this.lectureMapper.toDTO(lectures.get(0));
            }
            return null;
        }

}
