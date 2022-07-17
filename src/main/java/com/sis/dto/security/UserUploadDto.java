package com.sis.dto.security;

import com.sis.dto.BaseDTO;
import com.sis.util.Constants;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserUploadDto extends BaseDTO {


    @NotEmpty(message = "Required")
    @NotBlank(message = "Required")
    @Pattern(regexp = Constants.ARABIC_CHARACTERS, message = "Arabic Letters only")
    private String nameAr;

    @NotEmpty(message = "Required")
    @NotBlank(message = "Required")
    private String nationality;

    @NotEmpty(message = "Required")
    @NotBlank(message = "Required")
    @Pattern(regexp = Constants.DIGITS_ONLY_14, message = "must be 14 digits")
    private String nationalId;

    private String universityNumber;

    @NotEmpty(message = "Required")
    @NotBlank(message = "Required")
    private String collegeCode;

    @NotEmpty(message = "Required")
    @NotBlank(message = "Required")
    private String departmentCode;

    private String degreeID;

    private String errors;
    private Boolean isValid;
}
