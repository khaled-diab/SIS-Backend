package com.sis.dto.timetable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TimetableRequestDTO {
    private Long filterCollege;
    private Long filterDepartment;
    private Long filterAcademicYear;
    private Long filterAcademicTerm;
    private Long filterFacultyMember;
    private Long filterCourse;
    private Long filterSection;
    private String filterDay;
    private String sortDirection;
    private String sortBy;

}
