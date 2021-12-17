package com.sis.dto.facultyMember;

import com.sis.dto.BaseDTO;
import com.sis.dto.DegreeDTO;
import com.sis.dto.DepartmentDTO;
import com.sis.dto.college.CollegeDTO;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
public class FacultyMemberDTO extends BaseDTO {

	private String nameAr;
	private String nameEn;
	private String universityMail;
	private String alternativeMail;
	private String nationalID;
	private String nationality;
	private String phone;
	private Date birthDate;
	private DegreeDTO degreeDTO;
	private CollegeDTO collegeDTO;
	private DepartmentDTO departmentDTO;

}
