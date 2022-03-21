package com.sis.dto.section;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SectionRequestDTO {
    private String searchValue;
    private Long filterCollege;
    private Long filterDepartment;
    private Long filterAcademicYear;
    private Long filterAcademicTerm;
    private Long filterCourse;
    private Long filterStudyType;
    private Long filterMajor;
    private String sortDirection;
    private String sortBy;

}
