package com.sis.dto.gradeBook;

import com.sis.dto.*;
import com.sis.dto.section.SectionDTO;
import com.sis.dto.student.StudentDTO;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@Getter
@Setter
@Validated
public class GradeBookDTO extends BaseDTO {

    @NotNull(message = "Required")
    private AcademicTermDTO academicTermDTO;

    @NotNull(message = "Required")
    private StudentDTO studentDTO;

    @NotNull(message = "Required")
    private SectionDTO sectionDTO;

    @NotNull(message = "can't be empty")
    @Digits(integer = 3, fraction = 2)
    @PositiveOrZero
    private Double finalExamGrade;

    @Digits(integer = 3, fraction = 2)
    @PositiveOrZero
    private Double practicalGrade;

    @Digits(integer = 3, fraction = 2)
    @PositiveOrZero
    private Double oralGrade;

    @Digits(integer = 3, fraction = 2)
    @PositiveOrZero
    private Double midGrade;

}
