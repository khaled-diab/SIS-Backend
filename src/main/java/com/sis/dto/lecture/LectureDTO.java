package com.sis.dto.lecture;

import com.sis.dto.AcademicTermDTO;
import com.sis.dto.AcademicYearDTO;
import com.sis.dto.BaseDTO;
import com.sis.dto.course.CourseDTO;
import com.sis.dto.facultyMember.FacultyMemberDTO;
import com.sis.dto.section.SectionDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder
public class LectureDTO extends BaseDTO {

    private String lectureDay;

    private Date lectureDate;

    private LocalTime lectureStartTime;

    private LocalTime lectureEndTime;

    private long attendanceCode;

    private LocalTime attendanceCodeExpiringTime;

    private FacultyMemberDTO facultyMemberDTO;

    private CourseDTO courseDTO;

    private AcademicTermDTO academicTermDTO;

    private AcademicYearDTO academicYearDTO;

    private List<SectionDTO> sectionDTOs;

}
