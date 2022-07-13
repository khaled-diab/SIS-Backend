package com.sis.dto.student;

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
public class StudentUploadDto extends BaseDTO {


    @NotEmpty(message = "Required")
    @NotBlank(message = "Required")
    @Pattern(regexp = Constants.ARABIC_CHARACTERS, message = "Arabic Letters only")
    private String nameAr;

//    @NotEmpty(message = "Required")
//    @NotBlank(message = "Required")
//    @Pattern(regexp = Constants.ENGLISH_CHARACTERS, message = "English Letters only")
//    private String nameEn;

    @NotEmpty(message = "Required")
    @NotBlank(message = "Required")
    private String nationality;

    @NotEmpty(message = "Required")
    @NotBlank(message = "Required")
    @Pattern(regexp = Constants.DIGITS_ONLY_14, message = "must be 14 digits")
    private String nationalId;

//    @NotEmpty(message = "Required")
//    @NotBlank(message = "Required")
//    @Pattern(regexp = Constants.DATE_EXPRESSION, message = "the date must be like dd/mm/yyyy")
//    private Date birthDate;


    //    @Pattern(regexp = Constants.DIGITS_ONLY_11, message = "Invalid Mobile Number")
//    @NotEmpty(message = "Required")
//    @NotBlank(message = "Required")
//    private String phone;

    //    @Pattern(regexp = Constants.DIGITS_ONLY_11, message = "Invalid Mobile Number")
//    @NotEmpty(message = "Required")
//    @NotBlank(message = "Required")
//    private String parentPhone;

    @NotEmpty(message = "Required")
    @NotBlank(message = "Required")
    private String universityNumber;

    @NotEmpty(message = "Required")
    @NotBlank(message = "Required")
    private String collegeCode;

    @NotEmpty(message = "Required")
    @NotBlank(message = "Required")
    private String departmentCode;

    private String errors;
    private Boolean isValid;
}
