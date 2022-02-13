package com.sis.dto.studentEnrollment;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentEnrollmentRequestDTO {
    private String searchValue;
    private Long filterCollege;
    private Long filterDepartment;
    private String sortDirection;
    private String sortBy;

}
