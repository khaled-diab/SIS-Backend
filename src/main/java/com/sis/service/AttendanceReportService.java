package com.sis.service;

import com.sis.dao.AttendanceDetailsRepository;
import com.sis.dto.course.CourseDTO;
import com.sis.entities.FacultyMemberEnrollment;
import com.sis.entities.Lecture;
import com.sis.entities.mapper.CourseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public class AttendanceReportService extends BaseServiceImp<Lecture>{
    @Autowired
    AttendanceDetailsRepository attendanceDetailsRepository ;

    @Override
    public JpaRepository<Lecture, Long> Repository() {
        return null;
    }

    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private FacultyMemberEnrollmentService facultyMemberEnrollmentService;

    public ArrayList<CourseDTO> getFacultyMemberCourses(long academicYearId,
                                                        long academicTermId,
                                                        long facultyMemberId){
         ArrayList<FacultyMemberEnrollment> getFacutltyMembers =
            this.facultyMemberEnrollmentService.getFacultyMemberCourses(academicYearId,
                        academicTermId, facultyMemberId);
        ArrayList<CourseDTO> courses = new ArrayList<>();
        for(FacultyMemberEnrollment studentEnrollment : getFacutltyMembers){
            if(studentEnrollment!= null && studentEnrollment.getCourse() != null) {
                courses.add(this.courseMapper.toDTO(studentEnrollment.getCourse()));
            }
        }

        return courses;
    }


}
