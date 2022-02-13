package com.sis.dto.section;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SectionRequestDTO {
    private String searchValue;
    private Long filterCollege;
    private Long filterDepartment;
    private String sortDirection;
    private String sortBy;

}
