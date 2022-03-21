package com.sis.dto.studentEnrollment;

import com.sis.dto.student.StudentDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class StudentArray {

    private StudentEnrollmentDTO studentEnrollmentDTO;

    private ArrayList<StudentDTO> studentDTOS;
}
