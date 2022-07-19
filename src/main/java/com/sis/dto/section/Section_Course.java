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

    private String sectionNumber;

    private String courseName;

    private int lecturesNumber;

//    @NotNull(message = "Required")
//    private int presentsNumber;
//
//    @NotNull(message = "Required")
//    private int studentsNumber;
    private double presentAverage;
}
