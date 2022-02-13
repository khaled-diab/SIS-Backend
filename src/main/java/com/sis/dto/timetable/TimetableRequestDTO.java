package com.sis.dto.timetable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TimetableRequestDTO {
    private String searchValue;
    private Long filterCollege;
    private Long filterDepartment;
    private String sortDirection;
    private String sortBy;

}
