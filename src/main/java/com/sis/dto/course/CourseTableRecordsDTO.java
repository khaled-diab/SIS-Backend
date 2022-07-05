package com.sis.dto.course;

import com.sis.dto.BaseDTO;
import com.sis.util.Constants;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.*;

@Getter
@Setter
@Validated
public class CourseTableRecordsDTO extends BaseDTO {

    @NotNull(message = "can't be empty")
    private String code;

    @NotNull(message = "can't be empty")
    @Pattern(regexp = Constants.ARABIC_CHARACTERS, message = "Arabic letters only")
    private String nameAr;

    @NotNull(message = "can't be empty")
    @Pattern(regexp = Constants.ENGLISH_CHARACTERS, message = "English letters only")
    private String nameEn;

    @NotNull(message = "can't be empty")
    @Digits(integer = 3, fraction = 2)
    @Positive
    private Float finalGrade;

    @NotNull(message = "can't be null")
    private String collegeName;

    @NotNull(message = "can't be null")
    private String departmentName;

}
