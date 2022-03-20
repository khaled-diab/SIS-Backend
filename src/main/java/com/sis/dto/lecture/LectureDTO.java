package com.sis.dto.lecture;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
import java.util.Collection;
import java.util.Date;

@Getter
@Setter
//@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class LectureDTO extends BaseDTO {

    private String lectureDay;

    private String attendanceType;

    private Date lectureDate;

    private LocalTime lectureStartTime;

    private LocalTime lectureEndTime;

    private long attendanceCode;

    private boolean attendanceStatus;

    private FacultyMemberDTO facultyMemberDTO;

    private CourseDTO courseDTO;

    private AcademicTermDTO academicTermDTO;

    private AcademicYearDTO academicYearDTO;

    private SectionDTO sectionDTO;

    public boolean getAttendanceStatus() {
        return attendanceStatus;
    }

    public void setAttendanceStatus(boolean attendanceStatus) {
        this.attendanceStatus = attendanceStatus;
    }
}
