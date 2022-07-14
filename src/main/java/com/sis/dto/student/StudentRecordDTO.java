package com.sis.dto.student;

import com.sis.dto.BaseDTO;
import com.sis.util.Constants;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.*;

@Getter
@Setter
@Validated
public class StudentRecordDTO  extends BaseDTO {

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

    private  String level;
//    private  String year;

    /*Relations instances*/

    private String departmentName;

    @NotNull(message = "Required")
    private String collegeName;
}
