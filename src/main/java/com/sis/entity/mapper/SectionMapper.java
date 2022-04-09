package com.sis.entity.mapper;

import com.sis.dto.section.SectionDTO;
import com.sis.entity.Section;
import com.sis.util.PageResult;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

import static java.util.stream.Collectors.toCollection;

@Component
@AllArgsConstructor
public class SectionMapper implements Mapper<Section, SectionDTO> {

    private CollegeMapper collegeMapper;
    private DepartmentMapper departmentMapper;
    private AcademicYearMapper academicYearMapper;
    private AcademicTermMapper academicTermMapper;
    private CourseMapper courseMapper;
    private MajorMapper majorMapper;
    private StudyTypeMapper studyTypeMapper;

    @Override
    public ArrayList<SectionDTO> toDTOs(Collection<Section> entities) {
        return entities.stream().map(this::toDTO).collect(toCollection(ArrayList<SectionDTO>::new));
    }

    @Override
    public PageResult<SectionDTO> toDataPage(PageResult<Section> entities) {
        return new PageResult<>(entities.getData().stream().map(this::toDTO).collect(toCollection(ArrayList<SectionDTO>::new)), entities.getTotalCount(), entities.getPageSize(), entities.getCurrPage());
    }

    @Override
    public ArrayList<Section> toEntities(Collection<SectionDTO> dtos) {
        return dtos.stream().map(this::toEntity).collect(toCollection(ArrayList<Section>::new));
    }

    @Override
    public SectionDTO toDTO(Section entity) {
        SectionDTO dto = new SectionDTO();
        dto.setId(entity.getId());
        dto.setSectionNumber(entity.getSectionNumber());
        dto.setTheoreticalLectures(entity.getTheoreticalLectures());
        dto.setPracticalLectures(entity.getPracticalLectures());
        dto.setExercisesLectures(entity.getExercisesLectures());
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
        if (entity.getCourse() != null) {
            dto.setCourseDTO(courseMapper.toDTO(entity.getCourse()));
        }
        if (entity.getMajor() != null) {
            dto.setMajorDTO(majorMapper.toDTO(entity.getMajor()));
        }
        if (entity.getStudyType() != null) {
            dto.setStudyTypeDTO(studyTypeMapper.toDTO(entity.getStudyType()));
        }

        return dto;
    }

    @Override
    public Section toEntity(SectionDTO dto) {
        Section entity = new Section();
        entity.setId(dto.getId());
        entity.setSectionNumber(dto.getSectionNumber());
        entity.setTheoreticalLectures(dto.getTheoreticalLectures());
        entity.setPracticalLectures(dto.getPracticalLectures());
        entity.setExercisesLectures(dto.getExercisesLectures());
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
        if (dto.getCourseDTO() != null) {
            entity.setCourse(courseMapper.toEntity(dto.getCourseDTO()));
        }
        if (dto.getMajorDTO() != null) {
            entity.setMajor(majorMapper.toEntity(dto.getMajorDTO()));
        }
        if (dto.getStudyTypeDTO() != null) {
            entity.setStudyType(studyTypeMapper.toEntity(dto.getStudyTypeDTO()));
        }

        return entity;
    }

}
