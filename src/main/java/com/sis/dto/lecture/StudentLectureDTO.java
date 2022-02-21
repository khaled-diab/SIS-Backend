package com.sis.dto.lecture;

import com.sis.dto.student.StudentDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentLectureDTO {

    private LectureDTO lectureDTO;
    private StudentDTO studentDTO;
}
