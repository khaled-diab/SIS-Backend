package com.sis.dto.gradeBook;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class GradeBookRequestDTO {
    private Long filterAcademicTerm;
    private Long filterSection;
    private Long filterStudent;
    private String sortDirection;
    private String sortBy;
}
