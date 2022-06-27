package com.sis.service;

import com.sis.dto.attendanceReport.FacultyMemberLecturesDTO;
import com.sis.dto.lecture.LectureDTO;
import com.sis.entity.AttendanceDetails;
import com.sis.entity.Course;
import com.sis.entity.FacultyMember;
import com.sis.entity.Lecture;
import com.sis.entity.mapper.LectureMapper;
import com.sis.repository.LectureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;

import static java.util.stream.Collectors.toCollection;


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
                ArrayList<AttendanceDetails> presentAttendanceDetails =
                lecture.getAttendanceDetails().stream().filter(attendanceDetails1 ->
                        attendanceDetails1.getAttendanceStatus().equalsIgnoreCase(
                                "Present")).collect(toCollection(
                        ArrayList<AttendanceDetails>::new));
                ArrayList<AttendanceDetails> absetAttendance =
                        lecture.getAttendanceDetails().stream().filter(attendanceDetails1 ->
                        attendanceDetails1.getAttendanceStatus().equalsIgnoreCase(
                                "Absent")).collect(toCollection(
                        ArrayList<AttendanceDetails>::new));
                FacultyMemberLecturesDTO facultyMemberLecturesDTO  =new FacultyMemberLecturesDTO();
                facultyMemberLecturesDTO.setPresentStudent(presentAttendanceDetails.size());
                facultyMemberLecturesDTO.setAbsentStudent(absetAttendance.size());
                facultyMemberLecturesDTO.setRate(1.0*presentAttendanceDetails.size()/(absetAttendance.size()+presentAttendanceDetails.size()));
                facultyMemberLecturesDTO.setLectureEndTime(lecture.getLectureEndTime().toLocalTime());
                facultyMemberLecturesDTO.setLectureDay(lecture.getLectureDay());
                facultyMemberLecturesDTO.setLectureStartTime(lecture.getLectureStartTime().toLocalTime());
                facultyMemberLecturesDTO.setLectureDate(lecture.getLectureDate());
                facultyMemberLecturesDTO.setId(lecture.getId());
                facultyMemberLecturesDTOS.add(facultyMemberLecturesDTO);
            }
        }
        return facultyMemberLecturesDTOS;
    }

    public LectureDTO searchLecture(long sectionId ,Date lectureDate, Course  course, FacultyMember facultyMember,
                                    LocalTime lectureStartTime, LocalTime lectureEndTime)
    {

            ArrayList<Lecture> lectures = this.lectureRepository.findLectureBySectionIdAndLectureDateAndCourseIdAndFacultyMemberIdAndLectureStartTimeAndLectureEndTime
                    (sectionId,
                    lectureDate,
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
