package com.sis.dto.section;

import com.sis.dto.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Validated
public class SectionTableRecordsDTO extends BaseDTO {

    @NotNull(message = "Required")
    private String sectionNumber;

    private int theoreticalLectures;

    private int practicalLectures;

    private int exercisesLectures;

    private int numberOfStudents;

    private String majorName;

    @NotNull(message = "Required")
    private String studyTypeName;

    @NotNull(message = "Required")
    private String courseName;
}
