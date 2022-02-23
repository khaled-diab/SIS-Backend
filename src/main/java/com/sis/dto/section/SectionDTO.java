package com.sis.dto.section;

import com.sis.dto.*;
import com.sis.dto.college.CollegeDTO;
import com.sis.dto.course.CourseDTO;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import java.util.Collection;

@Getter
@Setter
@Validated
public class SectionDTO extends BaseDTO {

    private String sectionNumber;

    private int theoreticalLectures;

    private int practicalLectures;

    private int exercisesLectures;

    private MajorDTO majorDTO;

    private StudyTypeDTO studyTypeDTO;

    private CollegeDTO collegeDTO;

    private DepartmentDTO departmentDTO;

    private AcademicYearDTO academicYearDTO;

    private AcademicTermDTO academicTermDTO;

    private CourseDTO courseDTO;

}
