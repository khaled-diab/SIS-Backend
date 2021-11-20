package com.sis.dto.facultyMember;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FacultyMemberRequestDTO {
    private String searchValue;
    private Long filterCollege;
    private String sortDirection;
    private String sortBy;

}
