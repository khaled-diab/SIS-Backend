package com.sis.dto.course;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CourseRequestDTO {
    private String searchValue;
    private Long filterCollege;
    private String sortDirection;
    private String sortBy;


}
