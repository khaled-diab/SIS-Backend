package com.sis.dto.facultyMember;

import com.sis.dto.BaseDTO;
import com.sis.util.Constants;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@Validated
public class FacultyMemberTableRecordsDTO extends BaseDTO {

    @NotEmpty(message = "Required")
    @Pattern(regexp = Constants.ARABIC_CHARACTERS, message = "Arabic Letters only")
    private String nameAr;

    @NotEmpty(message = "Required")
    @Pattern(regexp = Constants.ENGLISH_CHARACTERS, message = "English Letters only")
    private String nameEn;

    @NotEmpty(message = "Required")
    @Email(message = "must be email")
    private String universityMail;

    @NotNull(message = "Required")
    private String degreeName;

    @NotNull(message = "Required")
    private String collegeName;

    @NotNull(message = "Required")
    private String departmentName;
}
