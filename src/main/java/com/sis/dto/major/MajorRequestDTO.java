package com.sis.dto.major;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MajorRequestDTO {
    private String searchValue;
    private Long filterCollege;
    private Long filterDepartment;
    private String sortDirection;
    private String sortBy;

}
