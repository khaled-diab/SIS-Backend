package com.sis.dto;

import com.sis.dto.college.CollegeDTO;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.persistence.Column;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.*;
import java.util.Date;
@Getter
@Setter
@Validated
public class StudentDTO extends BaseDTO {

    @NotNull(message = "Required")
    private long UniversityId;

    @NotEmpty(message = "Required")
    @NotBlank(message = "Required")
    @Pattern(regexp="^[\\u0621-\\u064A]+$",message="Arabic Letters only")
    private String nameAr;

    @NotEmpty(message = "Required")
    @NotBlank(message = "Required")
    @Pattern(regexp="[a-zA-Z ]+",message="English Letters only")

    private String nameEn;

    @NotEmpty(message = "Required")
    @NotBlank(message = "Required")
    private String nationality;

    @NotEmpty(message = "Required")
    @NotBlank(message = "Required")
    @Pattern(regexp="^[0-9]{14}",message="must be 14 digits")

    private String nationalId;

    private Date birthDate;

    @NotEmpty(message = "Required")
    @NotBlank(message = "Required")
    @Email(message = "must be email")
    private String universityMail;

    @Email(message = "must be email")
    private String alternativeMail;

    @NotEmpty(message = "Required")
    @NotBlank(message = "Required")
    @Pattern(regexp="^[0-9]{11}",message="Invalid Mobile Number")
    private String phone;
    @Pattern(regexp="^[0-9]{11}",message="Invalid Mobile Number")
    private String parentPhone;

    private  String level;
    private  String year;
    private  String photo;
    /*Relations instances*/

    private  DepartmentDTO departmentDTO;

    @NotNull(message = "Required")
    private CollegeDTO collegeDTO;

    @NotNull
    private AcademicProgramDTO academicProgramDTO;


}
