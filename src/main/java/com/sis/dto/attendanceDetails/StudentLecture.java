package com.sis.dto.attendanceDetails;

import com.sis.dto.lecture.LectureDTO;
import com.sis.dto.student.StudentDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentLecture {
    private Long studentId;
    private LectureDTO lectureDTO;
}
