package com.sis.dto;

import com.sis.dto.building.BuildingDTO;
import com.sis.dto.college.CollegeDTO;
import com.sis.util.Constants;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.*;

@Getter
@Setter
@Validated
public class ClassroomDTO extends BaseDTO{
    @NotNull(message = "Required")
    @NotEmpty(message = "Required")
    private String name;

    @NotNull(message = "Required")
    @NotEmpty(message = "Required")
    private String code;

    private int status;

    @NotNull(message = "Required")
    @Digits(integer = 4, fraction = 0)
    @Positive
    private int capacity;

    @NotNull(message = "Required")
    private CollegeDTO collegeDTO;

    @NotNull(message = "Required")
    private BuildingDTO buildingDTO;
}
