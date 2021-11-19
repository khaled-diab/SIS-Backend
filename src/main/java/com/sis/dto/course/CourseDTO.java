package com.sis.dto.course;


import com.sis.dto.BaseDTO;
import com.sis.dto.college.CollegeDTO;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Null;

@Getter
@Setter
public class CourseDTO extends BaseDTO {

    private String code;
    private String nameAr;
    private String nameEn;
    private Float theoreticalHours;
    private Float exercisesHours;
    private Float practicalHours;
    private Float totalHours;
    private Integer weeks;
    private Float finalGrade;
    private Float finalExamGrade;
    private Float practicalGrade;
    private Float oralGrade;
    private Float midGrade;
    private CollegeDTO collegeDTO;


}
