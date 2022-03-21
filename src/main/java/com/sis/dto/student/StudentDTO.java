package com.sis.dto.student;

import com.sis.dto.AcademicProgramDTO;
import com.sis.dto.BaseDTO;
import com.sis.dto.DepartmentDTO;
import com.sis.dto.attendanceDetails.AttendanceDetailsDTO;
import com.sis.dto.college.CollegeDTO;
import com.sis.util.Constants;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;



import javax.validation.constraints.*;

import java.util.Collection;
import java.util.Date;
@Getter
@Setter
@Validated
public class StudentDTO extends BaseDTO {

    @NotNull(message = "Required")
    private long UniversityId;

    @NotEmpty(message = "Required")
    @NotBlank(message = "Required")
    @Pattern(regexp= Constants.ARABIC_CHARACTERS,message="Arabic Letters only")
    private String nameAr;

    @NotEmpty(message = "Required")
    @NotBlank(message = "Required")
    @Pattern(regexp= Constants.ENGLISH_CHARACTERS, message="English Letters only")

    private String nameEn;

    @NotEmpty(message = "Required")
    @NotBlank(message = "Required")
    private String nationality;

    @NotEmpty(message = "Required")
    @NotBlank(message = "Required")
    @Pattern(regexp= Constants.DIGITS_ONLY_14, message="must be 14 digits")

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
    @Pattern(regexp= Constants.DIGITS_ONLY_11, message="Invalid Mobile Number")
    private String phone;

    @Pattern(regexp=Constants.DIGITS_ONLY_11, message="Invalid Mobile Number")
    private String parentPhone;

    private  String level;
    private  String year;
    private  String photo;
    /*Relations instances*/

    private DepartmentDTO departmentDTO;

    @NotNull(message = "Required")
    private CollegeDTO collegeDTO;

//    @NotNull
    private AcademicProgramDTO academicProgramDTO;



}
