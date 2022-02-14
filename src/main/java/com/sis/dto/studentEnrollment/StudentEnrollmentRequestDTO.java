package com.sis.dto.studentEnrollment;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentEnrollmentRequestDTO {
    private String searchValue;
    private Long filterCollege;
    private Long filterDepartment;
    private Long filterAcademicYear;
    private Long filterAcademicTerm;
    private Long filterCourse;
    private Long filterStudyType;
    private Long filterSectionNumber;
    private Long filterMajor;
    private String sortDirection;
    private String sortBy;

}
