package com.sis.dto.attendanceDetails;

import com.sis.dto.BaseDTO;
import com.sis.dto.course.CourseDTO;
import com.sis.dto.lecture.LectureDTO;
import com.sis.dto.section.SectionDTO;
import com.sis.dto.student.StudentDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


import java.time.LocalTime;
import java.util.Date;


@Getter
@Setter
@Builder
public class AttendanceDetailsDTO extends BaseDTO {


    private StudentDTO studentDTO;

    private LectureDTO lectureDTO;

    private String attendanceStatus;

    private Date attendanceDate;

    private LocalTime lectureStartTime;

    private LocalTime lectureEndTime;

    private SectionDTO sectionDTO;
}
