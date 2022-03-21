package com.sis.dto;

import com.sis.dto.college.CollegeDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MajorDTO extends BaseDTO {

    private String nameAr;
    private String nameEn;
    private CollegeDTO collegeDTO;
    private DepartmentDTO departmentDTO;
}
