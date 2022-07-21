package com.sis.dto.gradeBook;

import com.sis.dto.BaseDTO;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@Validated
public class GradeBookStudentRecordsDTO extends BaseDTO {

    private String studentNameAr;
    private String studentNameEn;
    private String studentUniversityId;

    private String courseNameAr;
    private String courseNameEn;

    private Double finalExamGrade;
    private Double courseFinalExamGrade;

    private Double practicalGrade;
    private Double coursePracticalGrade;

    private Double oralGrade;
    private Double courseOralGrade;

    private Double midGrade;
    private Double courseMidGrade;

    private Double courseTotalGrade;

}
