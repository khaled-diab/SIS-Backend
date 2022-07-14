package com.sis.dto.attendanceDetails;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class AttendanceBySection {
    private String nameOfStudent;
    private int numberOfLecture ;
    private int absentLecture ;
    private Long idOfStudent ;
    double rate ;
}
