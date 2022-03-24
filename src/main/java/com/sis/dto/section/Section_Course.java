package com.sis.dto.section;

import com.sis.dto.*;
import com.sis.dto.college.CollegeDTO;
import com.sis.dto.course.CourseDTO;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class Section_Course extends BaseDTO {

    @NotNull(message = "Required")
    private String sectionNumber;

    @NotNull(message = "Required")
    private String courseName;
}
