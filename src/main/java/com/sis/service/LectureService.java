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
    private TimetableService timetableService;

    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private TimetableMapper timetableMapper;

    @Autowired
    private StudentEnrollmentService studentEnrollmentService;
    @Autowired
    private SectionService sectionService;

    @Autowired
    private SectionMapper sectionMapper;

    @Override
    public JpaRepository<Lecture, Long> Repository() {
        return this.lectureRepository;
    }
//
    public Collection<CourseDTO> findByFacultyMemberCourses(long academicYearId, long academicTermId, long facultyMemberId){

        Collection<Course> facultyMemberCourses = this.facultyMemberEnrollmentService.findByFacultyMemberCourses(academicYearId,
                academicTermId, facultyMemberId);
        if(facultyMemberCourses != null) {
           return  this.courseMapper.toDTOs(facultyMemberCourses);
        }

        return null;
    }

    public Collection<TimetableDTO> findTimeTables(long academicYearId, long academicTermId, long facultyMemberId, long courseId){
        Collection<Timetable> timetables = this.timetableService.findTimeTables(academicYearId,  academicTermId,  facultyMemberId,  courseId);
        Collection<TimetableDTO> timetableDTOs= null;
        if(timetables != null){
            timetableDTOs = this.timetableMapper.toDTOs(timetables);
        }
        return  timetableDTOs;
    }

    public Collection<Section> findStudentSections(long academicYearId, long academicTermId,long studentId){
        Collection<Section> sections = this.studentEnrollmentService.findStudentSections(academicYearId, academicTermId,studentId);
        return sections;
    }

}
