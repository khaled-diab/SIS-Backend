package com.sis.dto.studentEnrollment;


import com.sis.dto.AcademicTermDTO;
import com.sis.dto.AcademicYearDTO;
import com.sis.dto.BaseDTO;
import com.sis.dto.student.StudentDTO;
import com.sis.dto.course.CourseDTO;
import com.sis.dto.section.SectionDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentEnrollmentDTO extends BaseDTO {

	private AcademicYearDTO academicYearDTO;

	private AcademicTermDTO academicTermDTO;

	private StudentDTO studentDTO;

	private CourseDTO courseDTO;

	private SectionDTO sectionDTO;

}
