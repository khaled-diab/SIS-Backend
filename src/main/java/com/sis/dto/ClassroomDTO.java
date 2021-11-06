package com.sis.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClassroomDTO extends BaseDTO{
    private String name_ar;
    private String name_en;
    private String code;
    private int status;
    private int capacity;
    private String departmentID;
    private BuildingDTO buildingDTO;
}
