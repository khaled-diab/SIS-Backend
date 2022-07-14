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

    private String nameAr;

    private String universityMail;

    private String degreeName;

    private String collegeName;

    private String departmentName;
}
