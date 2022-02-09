package com.sis.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentFilterDTO {
    private String filterValue;
    private String sortDirection;
    private String sortBy;
    private long collegeId;
    private long departmentId;

}
