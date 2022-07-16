package com.sis.service;

import com.sis.dto.AcademicTermDTO;
import com.sis.dto.attendanceReport.FacultyMemberLecturesDTO;
import com.sis.dto.lecture.LectureDTO;
import com.sis.entity.*;
import com.sis.entity.mapper.*;
import com.sis.repository.LectureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import static java.util.stream.Collectors.toCollection;


@Service
public class LectureService extends BaseServiceImp<Lecture> {


    @Autowired
    private LectureRepository lectureRepository;

    @Autowired
    private LectureMapper lectureMapper;

    @Autowired
    private CourseMapper courseMapper;
    @Autowired
    private FacultyMemberMapper facultyMemberMapper;
    @Autowired
    private AttendanceDetailsService attendanceDetailsService;
    @Autowired
    private AcademicTermService academicTermService;
    @Autowired
    private AcademicTermMapper academicTermMapper;
    @Autowired
    private AcademicYearMapper academicYearMapper;

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
                                                                                long sectionId) {
        ArrayList<Long> lectureIds = lectureRepository.findFacultyMemberLectures(sectionId);
        ArrayList<FacultyMemberLecturesDTO> facultyMemberLecturesDTOS = new ArrayList<>();
        for (Long id : lectureIds) {
            Lecture lecture = findById(id);
            if (lecture.getAcademicTermId().getId() ==
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
                FacultyMemberLecturesDTO facultyMemberLecturesDTO = new FacultyMemberLecturesDTO();
                facultyMemberLecturesDTO.setPresentStudent(presentAttendanceDetails.size());
                facultyMemberLecturesDTO.setAbsentStudent(absetAttendance.size());
                facultyMemberLecturesDTO.setRate(1.0 * presentAttendanceDetails.size()
                        / (absetAttendance.size() + presentAttendanceDetails.size()));
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

    public LectureDTO searchLecture(long sectionId, Date lectureDate, Course course, FacultyMember facultyMember,
                                    String lectureStartTime, String lectureEndTime) {

        System.out.println(sectionId + " sectionId " + lectureDate + " lectureDate " + lectureStartTime + " lectureStartTime " + lectureEndTime + " lectureEndTime ");
        ArrayList<Lecture> lectures = this.lectureRepository.findLectureBySectionIdAndLectureDateAndCourseIdAndFacultyMemberIdAndLectureStartTimeAndLectureEndTime
                (sectionId,
                        lectureDate,
                        course,
                        facultyMember,
                        lectureStartTime,
                        lectureEndTime);
        if (lectures != null && lectures.size() > 0) {
            return this.lectureMapper.toDTO(lectures.get(0));
        }
        return null;
    }


    public LectureDTO addLecture( LectureDTO lectureDTO) {
        Course course = this.courseMapper.toEntity(lectureDTO.getCourseDTO());
        FacultyMember facultyMember = this.facultyMemberMapper.toEntity(lectureDTO.getFacultyMemberDTO());
        AcademicTerm academicTerm = this.academicTermService.getCurrentAcademicTerm();
        AcademicTermDTO academicTermDTO = this.academicTermMapper.toDTO(academicTerm);
        lectureDTO.setAcademicTermDTO(academicTermDTO);
        lectureDTO.setAcademicYearDTO(this.academicYearMapper.toDTO(academicTerm.getAcademicYear()));

        if (!lectureDTO.getAttendanceType().equalsIgnoreCase("Manual")) {
            Random rand = new Random();
            lectureDTO.setAttendanceCode(Math.abs(rand.nextInt()));
        }
        boolean isFound = true;
        LectureDTO lectureDTO1 = this.searchLecture(lectureDTO.getSectionDTO().getId(),lectureDTO.getLectureDate(),course
                , facultyMember, lectureDTO.getLectureStartTime(), lectureDTO.getLectureEndTime());
        if (lectureDTO1 == null) {
            isFound = false;
        } else {
            lectureDTO.setId(lectureDTO1.getId());
        }
        Lecture lecture = this.lectureMapper.toEntity(lectureDTO);
        LectureDTO lectureDTO2 = this.lectureMapper.toDTO(this.save(lecture));
        if (!isFound) {
            this.attendanceDetailsService.saveAttendances(lectureDTO2);
        }
        return lectureDTO2;
    }
}
