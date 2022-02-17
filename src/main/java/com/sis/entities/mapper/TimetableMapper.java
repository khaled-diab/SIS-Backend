package com.sis.entities.mapper;

import com.sis.dto.timetable.TimetableDTO;
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
        dto.setId(entity.getId());
        dto.setDay(entity.getDay());
        dto.setStartTime(entity.getStartTime());
        dto.setEndTime(entity.getEndTime());
        if (entity.getLectureTypes() != null) {
            dto.setLectureTypeDTO(lectureTypeMapper.toDTOs(entity.getLectureTypes()));
        }
        if (entity.getCollege() != null) {
            dto.setCollegeDTO(collegeMapper.toDTO(entity.getCollege()));
        }
        if (entity.getDepartment() != null) {
            dto.setDepartmentDTO(departmentMapper.toDTO(entity.getDepartment()));
        }
        if (entity.getAcademicYear() != null) {
            dto.setAcademicYearDTO(academicYearMapper.toDTO(entity.getAcademicYear()));
        }
        if (entity.getAcademicTerm() != null) {
            dto.setAcademicTermDTO(academicTermMapper.toDTO(entity.getAcademicTerm()));
        }
        if (entity.getFacultyMembers() != null) {
            dto.setFacultyMemberDTO(facultyMemberMapper.toDTOs(entity.getFacultyMembers()));
        }
        if (entity.getCourses() != null) {
            dto.setCourseDTO(courseMapper.toDTOs(entity.getCourses()));
        }
        if (entity.getSections() != null) {
            dto.setSectionDTO(sectionMapper.toDTOs(entity.getSections()));
        }
        if (entity.getBuildings() != null) {
            dto.setBuildingDTO(buildingMapper.toDTOs(entity.getBuildings()));
        }
        if (entity.getClassrooms() != null) {
            dto.setClassroomDTO(classroomMapper.toDTOs(entity.getClassrooms()));
        }

        return dto;
    }

    @Override
    public Timetable toEntity(TimetableDTO dto) {
        Timetable entity = new Timetable();
        entity.setId(dto.getId());
        entity.setDay(dto.getDay());
        entity.setStartTime(dto.getStartTime());
        entity.setEndTime(dto.getEndTime());
        if (dto.getLectureTypeDTO() != null) {
            entity.setLectureTypes(lectureTypeMapper.toEntities(dto.getLectureTypeDTO()));
        }
        if (dto.getCollegeDTO() != null) {
            entity.setCollege(collegeMapper.toEntity(dto.getCollegeDTO()));
        }
        if (dto.getDepartmentDTO() != null) {
            entity.setDepartment(departmentMapper.toEntity(dto.getDepartmentDTO()));
        }
        if (dto.getAcademicYearDTO() != null) {
            entity.setAcademicYear(academicYearMapper.toEntity(dto.getAcademicYearDTO()));
        }
        if (dto.getAcademicTermDTO() != null) {
            entity.setAcademicTerm(academicTermMapper.toEntity(dto.getAcademicTermDTO()));
        }
        if (dto.getFacultyMemberDTO() != null) {
            entity.setFacultyMembers(facultyMemberMapper.toEntities(dto.getFacultyMemberDTO()));
        }
        if (dto.getCourseDTO() != null) {
            entity.setCourses(courseMapper.toEntities(dto.getCourseDTO()));
        }
        if (dto.getSectionDTO() != null) {
            entity.setSections(sectionMapper.toEntities(dto.getSectionDTO()));
        }
        if (dto.getBuildingDTO() != null) {
            entity.setBuildings(buildingMapper.toEntities(dto.getBuildingDTO()));
        }
        if (dto.getClassroomDTO() != null) {
            entity.setClassrooms(classroomMapper.toEntities(dto.getClassroomDTO()));
        }

        return entity;
    }

}
