package com.sis.dto.attendanceDetails;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sis.dto.BaseDTO;
import com.sis.dto.course.CourseDTO;
import com.sis.dto.lecture.LectureDTO;
import com.sis.dto.section.SectionDTO;
import com.sis.dto.student.StudentDTO;
import lombok.*;


import java.time.LocalTime;
import java.util.Date;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AttendanceDetailsDTO extends BaseDTO {


    private StudentDTO studentDTO;

    private LectureDTO lectureDTO;

    private String attendanceStatus;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date attendanceDate;

    private String lectureStartTime;

    private String lectureEndTime;

    private SectionDTO sectionDTO;
}
