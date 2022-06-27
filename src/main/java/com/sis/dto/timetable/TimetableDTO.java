package com.sis.dto.timetable;

import com.sis.dto.*;
import com.sis.dto.building.BuildingDTO;
import com.sis.dto.college.CollegeDTO;
import com.sis.dto.course.CourseDTO;
import com.sis.dto.facultyMember.FacultyMemberDTO;
import com.sis.dto.section.SectionDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Time;
import java.time.LocalTime;

@Getter
@Setter
@Validated

public class TimetableDTO extends BaseDTO {

    @NotEmpty(message = "Required")
    private String day;

//    @NotEmpty(message = "Required")
    private Time startTime;

//    @NotEmpty(message = "Required")
    private Time endTime;

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
