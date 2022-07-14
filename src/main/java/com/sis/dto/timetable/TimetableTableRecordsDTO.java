package com.sis.dto.timetable;

import com.sis.dto.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TimetableTableRecordsDTO extends BaseDTO {

    private String day;

    private String startTime;

    private String endTime;

    private String lectureTypeName;

    private String facultyMemberName;

    private String courseName;

    private String sectionName;

    private String buildingName;

    private String classroomName;
}
