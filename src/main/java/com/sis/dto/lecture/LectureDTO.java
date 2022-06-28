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
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.time.LocalTime;
import java.util.Collection;
import java.util.Date;


@Getter
@Setter
//@Builder
@Validated
@JsonIgnoreProperties(ignoreUnknown = true)
public class LectureDTO extends BaseDTO {

    @NotNull(message = "Required")
    private String lectureDay;

    @NotNull(message = "Required")
    private String attendanceType;

    @NotNull(message = "Required")
    private Date lectureDate;

    @NotNull(message = "Required")
    private String lectureStartTime;

    @NotNull(message = "Required")
    private String lectureEndTime;

    private long attendanceCode;

    @NotNull(message = "Required")
    private boolean attendanceStatus;

    @NotNull(message = "Required")
    private FacultyMemberDTO facultyMemberDTO;

    @NotNull(message = "Required")
    private CourseDTO courseDTO;


    private AcademicTermDTO academicTermDTO;


    private AcademicYearDTO academicYearDTO;

    @NotNull(message = "Required")
    private SectionDTO sectionDTO;

    public boolean getAttendanceStatus() {
        return attendanceStatus;
    }

    public void setAttendanceStatus(boolean attendanceStatus) {
        this.attendanceStatus = attendanceStatus;
    }
}
