package com.sis.dto;

import com.sis.dto.college.CollegeDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AcademicProgramDTO extends BaseDTO{
//     private Long  college_id , dept_id;
     private String code , name_ar , name_en ;
     private DepartmentDTO departmentDTO ;
     private CollegeDTO collegeDTO ;
}
