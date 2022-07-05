package com.sis.dto.attendanceReport;

import com.sis.dto.BaseDTO;
import com.sis.dto.attendanceDetails.AttendanceDetailsDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class AttendanceReportDTO extends BaseDTO {

    private ArrayList<AttendanceDetailsDTO> attendanceDetailsDTOs;

}
