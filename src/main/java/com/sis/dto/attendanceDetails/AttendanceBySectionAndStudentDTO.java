package com.sis.dto.attendanceDetails;

import com.sis.dto.BaseDTO;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;
@Getter
@Setter
public class AttendanceBySectionAndStudentDTO extends BaseDTO {
    private String attendanceDate ;
    private String AttendanceStatus;
    private String lectureEndTime;
    private String lectureStartTime;
    private  Long universityId;
}
