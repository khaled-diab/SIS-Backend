package com.sis.dto.attendanceReport;

import com.sis.dto.BaseDTO;
import com.sis.dto.attendanceDetails.AttendanceDetailsDTO;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Setter
@Getter
public class FacultyMemberLecturesDTO extends BaseDTO {

    private String lectureDay;

    private Date lectureDate;

    private int presentStudent;

    private int absentStudent;

    private double rate ;

    private String lectureStartTime;

    private String lectureEndTime;


}
