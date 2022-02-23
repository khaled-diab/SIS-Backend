package com.sis.dto.studentEnrollment;


import com.sis.dto.*;
import com.sis.dto.college.CollegeDTO;
import com.sis.dto.course.CourseDTO;
import com.sis.dto.section.SectionDTO;
import com.sis.dto.student.StudentDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;

@Getter
@Setter
public class StudentEnrollmentDTO extends BaseDTO {

	private CollegeDTO collegeDTO;

	private DepartmentDTO departmentDTO;

	private AcademicYearDTO academicYearDTO;

	private AcademicTermDTO academicTermDTO;

	private StudentDTO studentDTO;

	private CourseDTO courseDTO;

	private SectionDTO sectionDTO;

}
