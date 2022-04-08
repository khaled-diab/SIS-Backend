package com.sis.dto.student;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class StudentFilterDTO {
    private String filterValue;
    private String sortDirection;
    private String sortBy;
    private Long collegeId;
    private Long departmentId;
    private String level;
}
