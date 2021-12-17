package com.sis.dto.course;


import com.sis.dto.BaseDTO;
import com.sis.dto.college.CollegeDTO;
import com.sis.util.Constants;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.lang.Nullable;
import org.springframework.validation.annotation.Validated;

import javax.persistence.UniqueConstraint;
import javax.validation.constraints.*;

@Getter
@Setter
@Validated
public class CourseDTO extends BaseDTO {

    @NotNull(message = "can't be empty")
    private String code;

    @NotNull(message = "can't be empty")
    @Pattern(regexp = Constants.ARABIC_CHARACTERS, message = "Arabic letters only")
    private String nameAr;

    @NotNull(message = "can't be empty")
    @Pattern(regexp = Constants.ENGLISH_CHARACTERS, message = "English letters only")
    private String nameEn;

    @Digits(integer = 3, fraction = 2)
    @PositiveOrZero
    private Float theoreticalHours;

    @Digits(integer = 3, fraction = 2)
    @PositiveOrZero
    private Float exercisesHours;

    @Digits(integer = 3, fraction = 2)
    @PositiveOrZero
    private Float practicalHours;

    @NotNull(message = "can't be empty")
    @Digits(integer = 3, fraction = 2)
    @Positive
    private Float totalHours;

    @NotNull(message = "can't be empty")
    @Digits(integer = 2, fraction = 0)
    @Positive
    private Integer weeks;

    @NotNull(message = "can't be empty")
    @Digits(integer = 3, fraction = 2)
    @Positive
    private Float finalGrade;

    @NotNull(message = "can't be empty")
    @Digits(integer = 3, fraction = 2)
    @Positive
    private Float finalExamGrade;

    @Digits(integer = 3, fraction = 2)
    @Positive
    private Float practicalGrade;

    @Digits(integer = 3, fraction = 2)
    @Positive
    private Float oralGrade;

    @Digits(integer = 3, fraction = 2)
    @Positive
    private Float midGrade;

    @NotNull(message = "can't be null")
    private CollegeDTO collegeDTO;


}
