package com.sis.dto.FacultyMemberEnrollment;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FacultyMemberEnrollmentRequestDTO {
    private String searchValue;
    private Long filterCollege;
    private Long filterDepartment;
    private Long filterAcademicYear;
    private Long filterAcademicTerm;
    private Long filterFacultyMember;
    private String sortDirection;
    private String sortBy;

}
