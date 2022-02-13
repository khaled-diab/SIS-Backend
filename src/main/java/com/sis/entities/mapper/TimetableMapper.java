package com.sis.entities.mapper;

import com.sis.dto.*;
import com.sis.dto.building.BuildingDTO;
import com.sis.dto.course.CourseDTO;
import com.sis.dto.facultyMember.FacultyMemberDTO;
import com.sis.dto.section.SectionDTO;
import com.sis.dto.timetable.TimetableDTO;
import com.sis.dto.college.CollegeDTO;
import com.sis.entities.*;
import com.sis.util.PageResult;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

import static java.util.stream.Collectors.toCollection;

@Component
@AllArgsConstructor
public class TimetableMapper implements Mapper<Timetable, TimetableDTO> {

    private LectureTypeMapper lectureTypeMapper;
    private CollegeMapper collegeMapper;
    private DepartmentMapper departmentMapper;
    private AcademicYearMapper academicYearMapper;
    private AcademicTermMapper academicTermMapper;
    private FacultyMemberMapper facultyMemberMapper;
    private CourseMapper courseMapper;
    private SectionMapper sectionMapper;
    private BuildingMapper buildingMapper;
    private ClassroomMapper classroomMapper;

    @Override
    public ArrayList<TimetableDTO> toDTOs(Collection<Timetable> entities) {
        return entities.stream().map(this::toDTO).collect(toCollection(ArrayList<TimetableDTO>::new));
    }

    @Override
    public PageResult<TimetableDTO> toDataPage(PageResult<Timetable> entities) {
        return new PageResult<>(entities.getData().stream().map(this::toDTO).collect(toCollection(ArrayList<TimetableDTO>::new)), entities.getTotalCount(), entities.getPageSize(), entities.getCurrPage());
    }

    @Override
    public ArrayList<Timetable> toEntities(Collection<TimetableDTO> dtos) {
        return dtos.stream().map(this::toEntity).collect(toCollection(ArrayList<Timetable>::new));
    }

    @Override
    public TimetableDTO toDTO(Timetable entity) {
        TimetableDTO dto = new TimetableDTO();
        LectureTypeDTO lectureTypeDTO = lectureTypeMapper.toDTO((LectureType) entity.getLectureType());
        CollegeDTO collegeDTO = collegeMapper.toDTO(entity.getCollege());
        DepartmentDTO departmentDTO = departmentMapper.toDTO(entity.getDepartment());
        AcademicYearDTO academicYearDTO = academicYearMapper.toDTO(entity.getAcademicYear());
        AcademicTermDTO academicTermDTO = academicTermMapper.toDTO(entity.getAcademicTerm());
        FacultyMemberDTO facultyMemberDTO = facultyMemberMapper.toDTO((FacultyMember) entity.getFacultyMember());
        CourseDTO courseDTO = courseMapper.toDTO((Course) entity.getCourse());
        SectionDTO sectionDTO = sectionMapper.toDTO((Section) entity.getSection());
        BuildingDTO buildingDTO = buildingMapper.toDTO((Building) entity.getBuilding());
        ClassroomDTO classroomDTO = classroomMapper.toDTO((Classroom) entity.getClassroom());

        dto.setId(entity.getId());
        dto.setDay(entity.getDay());
        dto.setStartTime(entity.getStartTime());
        dto.setEndTime(entity.getEndTime());
        if (entity.getLectureType() != null) {
            dto.setLectureTypeDTO(lectureTypeDTO);
        }
        if (entity.getCollege() != null) {
            dto.setCollegeDTO(collegeDTO);
        }
        if (entity.getDepartment() != null) {
            dto.setDepartmentDTO(departmentDTO);
        }
        if (entity.getAcademicYear() != null) {
            dto.setAcademicYearDTO(academicYearDTO);
        }
        if (entity.getAcademicTerm() != null) {
            dto.setAcademicTermDTO(academicTermDTO);
        }
        if (entity.getFacultyMember() != null) {
            dto.setFacultyMemberDTO(facultyMemberDTO);
        }
        if (entity.getCourse() != null) {
            dto.setCourseDTO(courseDTO);
        }
        if (entity.getSection() != null) {
            dto.setSectionDTO(sectionDTO);
        }
        if (entity.getBuilding() != null) {
            dto.setBuildingDTO(buildingDTO);
        }
        if (entity.getClassroom() != null) {
            dto.setClassroomDTO(classroomDTO);
        }

        return dto;
    }

    @Override
    public Timetable toEntity(TimetableDTO dto) {
        Timetable entity = new Timetable();
        LectureType lectureType = lectureTypeMapper.toEntity(dto.getLectureTypeDTO());
        College college = collegeMapper.toEntity(dto.getCollegeDTO());
        Department department = departmentMapper.toEntity(dto.getDepartmentDTO());
        AcademicYear academicYear = academicYearMapper.toEntity(dto.getAcademicYearDTO());
        AcademicTerm academicTerm = academicTermMapper.toEntity(dto.getAcademicTermDTO());
        FacultyMember facultyMember = facultyMemberMapper.toEntity(dto.getFacultyMemberDTO());
        Course course = courseMapper.toEntity(dto.getCourseDTO());
        Section section = sectionMapper.toEntity(dto.getSectionDTO());
        Building building = buildingMapper.toEntity(dto.getBuildingDTO());
        Classroom classroom = classroomMapper.toEntity(dto.getClassroomDTO());

        entity.setId(dto.getId());
        entity.setDay(dto.getDay());
        entity.setStartTime(dto.getStartTime());
        entity.setEndTime(dto.getEndTime());
        if (dto.getLectureTypeDTO() != null) {
            entity.setLectureType((Collection<LectureType>) lectureType);
        }
        if (dto.getCollegeDTO() != null) {
            entity.setCollege(college);
        }
        if (dto.getDepartmentDTO() != null) {
            entity.setDepartment(department);
        }
        if (dto.getAcademicYearDTO() != null) {
            entity.setAcademicYear(academicYear);
        }
        if (dto.getAcademicTermDTO() != null) {
            entity.setAcademicTerm(academicTerm);
        }
        if (dto.getFacultyMemberDTO() != null) {
            entity.setFacultyMember((Collection<FacultyMember>) facultyMember);
        }
        if (dto.getCourseDTO() != null) {
            entity.setCourse((Collection<Course>) course);
        }
        if (dto.getSectionDTO() != null) {
            entity.setSection((Collection<Section>) section);
        }
        if (dto.getBuildingDTO() != null) {
            entity.setBuilding((Collection<Building>) building);
        }
        if (dto.getClassroomDTO() != null) {
            entity.setClassroom((Collection<Classroom>) classroom);
        }

        return entity;
    }

}
