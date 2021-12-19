package com.sis.dto;

import com.sis.dto.building.BuildingDTO;
import com.sis.entities.Department;
import com.sis.util.Constants;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.*;

@Getter
@Setter
@Validated
public class ClassroomDTO extends BaseDTO{
    @NotNull(message = "Required")
    @NotEmpty(message = "Required")
    @Pattern(regexp = Constants.ARABIC_CHARACTERS_OR_DIGITS, message = "Arabic Letters and/or Digits")
    private String nameAr;

    @NotNull(message = "Required")
    @NotEmpty(message = "Required")
    @Pattern(regexp = Constants.ENGLISH_CHARACTERS_OR_DIGITS, message = "English Letters and/or Digits")
    private String nameEn;

    @NotNull(message = "Required")
    @NotEmpty(message = "Required")
    private String code;

    private int status;

    @NotNull(message = "Required")
    @Digits(integer = 4, fraction = 0)
    @Positive
    private int capacity;

    @NotNull(message = "Required")
    private DepartmentDTO departmentDTO;

    @NotNull(message = "Required")
    private BuildingDTO buildingDTO;
}
