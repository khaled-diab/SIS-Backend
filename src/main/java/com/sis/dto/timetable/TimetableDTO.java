package com.sis.dto.timetable;

import com.sis.dto.*;
import com.sis.dto.building.BuildingDTO;
import com.sis.dto.college.CollegeDTO;
import com.sis.dto.course.CourseDTO;
import com.sis.dto.facultyMember.FacultyMemberDTO;
import com.sis.dto.section.SectionDTO;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Validated
public class TimetableDTO extends BaseDTO {

    @NotEmpty(message = "Required")
    private String day;

//    @NotEmpty(message = "Required")
    private String startTime;

//    @NotEmpty(message = "Required")
    private String endTime;

    @NotNull(message = "Required")
    private LectureTypeDTO lectureTypeDTO;

    @NotNull(message = "Required")
    private CollegeDTO collegeDTO;

    @NotNull(message = "Required")
    private DepartmentDTO departmentDTO;

    @NotNull(message = "Required")
    private AcademicYearDTO academicYearDTO;

    @NotNull(message = "Required")
    private AcademicTermDTO academicTermDTO;

    @NotNull(message = "Required")
    private FacultyMemberDTO facultyMemberDTO;

    @NotNull(message = "Required")
    private CourseDTO courseDTO;

//    @NotNull(message = "Required")
    private SectionDTO sectionDTO;

    @NotNull(message = "Required")
    private BuildingDTO buildingDTO;

    @NotNull(message = "Required")
    private ClassroomDTO classroomDTO;

}
