package com.sis.dto.FacultyMemberEnrollment;


import com.sis.dto.AcademicTermDTO;
import com.sis.dto.AcademicYearDTO;
import com.sis.dto.BaseDTO;
import com.sis.dto.DepartmentDTO;
import com.sis.dto.college.CollegeDTO;
import com.sis.dto.course.CourseDTO;
import com.sis.dto.facultyMember.FacultyMemberDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;

@Getter
@Setter
public class FacultyMemberEnrollmentDTO extends BaseDTO {

	private CollegeDTO collegeDTO;

	private DepartmentDTO departmentDTO;

	private AcademicYearDTO academicYearDTO;

	private AcademicTermDTO academicTermDTO;

	private FacultyMemberDTO facultyMemberDTO;

	private Collection<CourseDTO> courseDTO;

}
