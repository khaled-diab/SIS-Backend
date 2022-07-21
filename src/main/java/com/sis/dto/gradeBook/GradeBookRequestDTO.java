package com.sis.dto.gradeBook;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class GradeBookRequestDTO {
    private Long filterAcademicTerm;
    private Long filterCourse;
    private Long filterStudent;
//    private Long filterFacultyMember;
    private String sortDirection;
    private String sortBy;
}
