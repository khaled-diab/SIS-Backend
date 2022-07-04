package com.sis.dto.attendanceDetails;

import lombok.Getter;
import lombok.Setter;
import java.util.Date;
@Getter
@Setter
public class AttendanceBySectionAndStudentDTO {
    private Date attendanceDate ;
    private String AttendanceStatus;
    private String lectureEndTime;
    private String lectureStartTime;
}
