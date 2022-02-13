package com.sis.dto.FacultyMemberEnrollment;


import com.sis.dto.AcademicTermDTO;
import com.sis.dto.AcademicYearDTO;
import com.sis.dto.BaseDTO;
import com.sis.dto.course.CourseDTO;
import com.sis.dto.facultyMember.FacultyMemberDTO;
import com.sis.dto.section.SectionDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FacultyMemberEnrollmentDTO extends BaseDTO {

	private AcademicYearDTO academicYearDTO;

	private AcademicTermDTO academicTermDTO;

	private FacultyMemberDTO facultyMemberDTO;

	private CourseDTO courseDTO;

	private SectionDTO sectionDTO;

}
