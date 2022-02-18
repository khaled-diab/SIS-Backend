package com.sis.dto.attendanceDetails;

import com.sis.dto.BaseDTO;
import com.sis.dto.lecture.LectureDTO;
import com.sis.dto.student.StudentDTO;
import com.sis.entities.Lecture;
import com.sis.entities.Student;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder
public class AttendanceDetailsDTO extends BaseDTO {


    private List<StudentDTO> studentDTOs;


    private List<LectureDTO> lectureDTOs;


    private String attendanceStatus;


    private Date attendanceDate;


    private LocalTime lectureStartTime;

    private LocalTime lectureEndTime;
}
