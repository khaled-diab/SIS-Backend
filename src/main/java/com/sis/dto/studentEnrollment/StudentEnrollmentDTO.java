package com.sis.dto.studentEnrollment;


import com.sis.dto.*;
import com.sis.dto.college.CollegeDTO;
import com.sis.dto.course.CourseDTO;
import com.sis.dto.section.SectionDTO;
import com.sis.dto.student.StudentDTO;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Collection;

@Getter
@Setter
@Validated
public class StudentEnrollmentDTO extends BaseDTO {

    @NotNull(message = "Required")
    private CollegeDTO collegeDTO;

    @NotNull(message = "Required")
    private DepartmentDTO departmentDTO;

    @NotNull(message = "Required")
    private AcademicYearDTO academicYearDTO;

    @NotNull(message = "Required")
    private AcademicTermDTO academicTermDTO;

    @NotNull(message = "Required")
    private StudentDTO studentDTO;

    @NotNull(message = "Required")
    private CourseDTO courseDTO;

    @NotNull(message = "Required")
    private SectionDTO sectionDTO;

    private MajorDTO majorDTO;

    @NotNull(message = "Required")
    private StudyTypeDTO studyTypeDTO;

}
