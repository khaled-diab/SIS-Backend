package com.sis.entity.mapper;

import com.sis.dto.timetable.TimetableDTO;
import com.sis.entity.Timetable;
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
    public ArrayList<TimetableDTO> toDTOs(Collection<Timetable> entity) {
        return entity.stream().map(this::toDTO).collect(toCollection(ArrayList<TimetableDTO>::new));
    }

    @Override
    public PageResult<TimetableDTO> toDataPage(PageResult<Timetable> entity) {
        return new PageResult<>(entity.getData().stream().map(this::toDTO).collect(toCollection(ArrayList<TimetableDTO>::new)), entity.getTotalCount(), entity.getPageSize(), entity.getCurrPage());
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
        if (entity.getLectureType() != null) {
            dto.setLectureTypeDTO(lectureTypeMapper.toDTO(entity.getLectureType()));
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
        if (entity.getFacultyMember() != null) {
            dto.setFacultyMemberDTO(facultyMemberMapper.toDTO(entity.getFacultyMember()));
        }
        if (entity.getCourse() != null) {
            dto.setCourseDTO(courseMapper.toDTO(entity.getCourse()));
        }
        if (entity.getSection() != null) {
            dto.setSectionDTO(sectionMapper.toDTO(entity.getSection()));
        }
        if (entity.getBuilding() != null) {
            dto.setBuildingDTO(buildingMapper.toDTO(entity.getBuilding()));
        }
        if (entity.getClassroom() != null) {
            dto.setClassroomDTO(classroomMapper.toDTO(entity.getClassroom()));
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
            entity.setLectureType(lectureTypeMapper.toEntity(dto.getLectureTypeDTO()));
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
            entity.setFacultyMember(facultyMemberMapper.toEntity(dto.getFacultyMemberDTO()));
        }
        if (dto.getCourseDTO() != null) {
            entity.setCourse(courseMapper.toEntity(dto.getCourseDTO()));
        }
        if (dto.getSectionDTO() != null) {
            entity.setSection(sectionMapper.toEntity(dto.getSectionDTO()));
        }
        if (dto.getBuildingDTO() != null) {
            entity.setBuilding(buildingMapper.toEntity(dto.getBuildingDTO()));
        }
        if (dto.getClassroomDTO() != null) {
            entity.setClassroom(classroomMapper.toEntity(dto.getClassroomDTO()));
        }

        return entity;
    }

}
