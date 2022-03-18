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

import java.time.LocalTime;
import java.util.Collection;

@Getter
@Setter
@Validated
public class TimetableDTO extends BaseDTO {

    private String day;

    private LocalTime startTime;

    private LocalTime endTime;

    private LectureTypeDTO lectureTypeDTO;

    private CollegeDTO collegeDTO;

    private DepartmentDTO departmentDTO;

    private AcademicYearDTO academicYearDTO;

    private AcademicTermDTO academicTermDTO;

    private FacultyMemberDTO facultyMemberDTO;

    private CourseDTO courseDTO;

    private SectionDTO sectionDTO;

    private BuildingDTO buildingDTO;

    private ClassroomDTO classroomDTO;

}
