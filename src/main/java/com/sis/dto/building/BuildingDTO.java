package com.sis.dto.building;

import com.sis.dto.BaseDTO;
import com.sis.dto.ClassroomDTO;
import com.sis.dto.college.CollegeDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;

@Getter
@Setter
public class BuildingDTO extends BaseDTO {
    private String nameAr;
    private String nameEn;
    private String code;
    private int status;
    private CollegeDTO collegeDTO;
}
