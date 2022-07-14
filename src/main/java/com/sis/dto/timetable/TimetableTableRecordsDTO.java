package com.sis.dto.timetable;

import com.sis.dto.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Validated
public class TimetableTableRecordsDTO extends BaseDTO {

    @NotEmpty(message = "Required")
    private String day;

    private String startTime;

    private String endTime;

    @NotNull(message = "Required")
    private String lectureTypeName;

    @NotNull(message = "Required")
    private String facultyMemberName;

    @NotNull(message = "Required")
    private String courseName;

    private String sectionName;

    @NotNull(message = "Required")
    private String buildingName;

    @NotNull(message = "Required")
    private String classroomName;
}
