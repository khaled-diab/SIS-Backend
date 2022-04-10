package com.sis.dto.student;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentFilterDTO {
    private String filterValue;
    private String sortDirection;
    private String sortBy;
    private Long collegeId;
    private Long departmentId;
    private String level;

}
