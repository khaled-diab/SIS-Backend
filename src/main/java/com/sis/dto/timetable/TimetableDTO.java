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

@Getter
@Setter
@Validated
public class TimetableDTO extends BaseDTO {

    private String day;

    private Float startTime;

    private Float endTime;

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
