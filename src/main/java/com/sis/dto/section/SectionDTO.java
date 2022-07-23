package com.sis.dto.section;

import com.sis.dto.*;
import com.sis.dto.college.CollegeDTO;
import com.sis.dto.course.CourseDTO;
import com.sis.dto.major.MajorDTO;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Validated
public class SectionDTO extends BaseDTO {

    @NotNull(message = "Required")
    private String sectionNumber;

    private int theoreticalLectures;

    private int practicalLectures;

    private int exercisesLectures;

    private int numberOfStudents;

    @NotNull(message = "Required")
    private int capacity;

    private MajorDTO majorDTO;

    @NotNull(message = "Required")
    private StudyTypeDTO studyTypeDTO;

    @NotNull(message = "Required")
    private CollegeDTO collegeDTO;

    @NotNull(message = "Required")
    private DepartmentDTO departmentDTO;

    @NotNull(message = "Required")
    private AcademicYearDTO academicYearDTO;

    @NotNull(message = "Required")
    private AcademicTermDTO academicTermDTO;

    @NotNull(message = "Required")
    private CourseDTO courseDTO;
}
