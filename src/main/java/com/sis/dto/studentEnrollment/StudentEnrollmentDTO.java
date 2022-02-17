package com.sis.dto.studentEnrollment;


import com.sis.dto.*;
import com.sis.dto.college.CollegeDTO;
import com.sis.dto.course.CourseDTO;
import com.sis.dto.section.SectionDTO;
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

	private Collection<StudentDTO> studentDTO;

	private Collection<CourseDTO> courseDTO;

	private Collection<SectionDTO> sectionDTO;

}
