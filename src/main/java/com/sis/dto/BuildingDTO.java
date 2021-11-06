package com.sis.dto;

import com.sis.dto.college.CollegeDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;

@Getter
@Setter
public class BuildingDTO extends BaseDTO{
    private String name_ar;
    private String name_en;
    private String code;
    private int status;
    private CollegeDTO collegeDTO;
    private Collection<ClassroomDTO> classroomDTOCollection;
}
