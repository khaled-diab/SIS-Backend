package com.sis.dto.building;

import com.sis.dto.BaseDTO;
import com.sis.dto.college.CollegeDTO;
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
public class BuildingDTO extends BaseDTO {
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
    private CollegeDTO collegeDTO;
}
