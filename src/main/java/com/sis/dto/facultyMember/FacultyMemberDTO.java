package com.sis.dto.facultyMember;

import com.sis.dto.BaseDTO;
import com.sis.dto.DegreeDTO;
import com.sis.dto.DepartmentDTO;
import com.sis.dto.college.CollegeDTO;
import com.sis.entities.security.User;
import com.sis.util.Constants;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.Date;

@Getter
@Setter
@Validated
public class FacultyMemberDTO extends BaseDTO {

    @NotEmpty(message = "Required")
    @Pattern(regexp = Constants.ARABIC_CHARACTERS, message = "Arabic Letters only")
    private String nameAr;

    @NotEmpty(message = "Required")
    @Pattern(regexp = Constants.ENGLISH_CHARACTERS, message = "English Letters only")
    private String nameEn;

    @NotEmpty(message = "Required")
    @Email(message = "must be email")
    private String universityMail;

    @NotEmpty(message = "Required")
    @Email(message = "must be email")
    private String alternativeMail;

    @NotEmpty(message = "Required")
    @Pattern(regexp = Constants.DIGITS_ONLY_14, message = "must be 14 digits")
    private String nationalID;

    @NotEmpty(message = "Required")
    private String nationality;

    @NotEmpty(message = "Required")
    @Pattern(regexp = Constants.DIGITS_ONLY_11, message = "Invalid Mobile Number")
    private String phone;

    //	@NotEmpty(message = "Required")
    private String photo;

    @NotEmpty(message = "Required")
    private Date birthDate;

    @NotEmpty(message = "Required")
    private DegreeDTO degreeDTO;

    @NotEmpty(message = "Required")
    private CollegeDTO collegeDTO;

    @NotEmpty(message = "Required")
    private DepartmentDTO departmentDTO;

    private User user;

}
