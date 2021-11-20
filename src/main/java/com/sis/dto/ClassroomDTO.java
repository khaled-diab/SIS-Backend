package com.sis.dto;

import com.sis.dto.building.BuildingDTO;
import com.sis.entities.Department;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClassroomDTO extends BaseDTO{
    private String nameAr;
    private String nameEn;
    private String code;
    private int status;
    private int capacity;
    private DepartmentDTO departmentDTO;
    private BuildingDTO buildingDTO;
}
