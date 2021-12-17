package com.sis.dto;

import com.sis.dto.building.BuildingDTO;
import com.sis.entities.Department;
import com.sis.util.Constants;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@Validated
public class ClassroomDTO extends BaseDTO{
    @NotNull(message = "Required")
    @NotEmpty(message = "Required")
    @Pattern(regexp = Constants.ARABIC_CHARACTERS, message = "Arabic Letters only")
    private String nameAr;

    @NotNull(message = "Required")
    @NotEmpty(message = "Required")
    @Pattern(regexp = Constants.ENGLISH_CHARACTERS, message = "English Letters only")
    private String nameEn;

    @NotNull(message = "Required")
    @NotEmpty(message = "Required")
    private String code;

    private int status;

    @NotNull(message = "Required")
    @NotEmpty(message = "Required")
    @Pattern(regexp = Constants.DIGITS_ONLY, message = "Numerical Digits only")
    private int capacity;

    @NotNull(message = "Required")
    private DepartmentDTO departmentDTO;

    @NotNull(message = "Required")
    private BuildingDTO buildingDTO;
}
