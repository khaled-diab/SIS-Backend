package com.sis.dto.section;

import com.sis.dto.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SectionTableRecordsDTO extends BaseDTO {

    private String sectionNumber;

    private int numberOfStudents;

    private int capacity;

    private String majorName;

    private String studyTypeName;

    private String courseName;

    private String collegeName;

    private String departmentName;
}
