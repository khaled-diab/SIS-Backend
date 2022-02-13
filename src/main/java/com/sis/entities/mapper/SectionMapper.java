package com.sis.entities.mapper;

import com.sis.dto.*;
import com.sis.dto.college.CollegeDTO;
import com.sis.dto.course.CourseDTO;
import com.sis.dto.section.SectionDTO;
import com.sis.entities.*;
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
        CollegeDTO collegeDTO = collegeMapper.toDTO(entity.getCollege());
        DepartmentDTO departmentDTO = departmentMapper.toDTO(entity.getDepartment());
        AcademicYearDTO academicYearDTO = academicYearMapper.toDTO(entity.getAcademicYear());
        AcademicTermDTO academicTermDTO = academicTermMapper.toDTO(entity.getAcademicTerm());
        CourseDTO courseDTO = courseMapper.toDTO((Course) entity.getCourse());
        MajorDTO majorDTO = majorMapper.toDTO(entity.getMajor());
        StudyTypeDTO studyTypeDTO = studyTypeMapper.toDTO(entity.getStudyType());

        dto.setId(entity.getId());
        dto.setSectionNumber(entity.getSectionNumber());
        dto.setTheoreticalLectures(entity.getTheoreticalLectures());
        dto.setPracticalLectures(entity.getPracticalLectures());
        dto.setExercisesLectures(entity.getExercisesLectures());
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
        if (entity.getCourse() != null) {
            dto.setCourseDTO(courseDTO);
        }
        if (entity.getMajor() != null) {
            dto.setMajorDTO(majorDTO);
        }
        if (entity.getStudyType() != null) {
            dto.setStudyTypeDTO(studyTypeDTO);
        }

        return dto;
    }

    @Override
    public Section toEntity(SectionDTO dto) {
        Section entity = new Section();
        College college = collegeMapper.toEntity(dto.getCollegeDTO());
        Department department = departmentMapper.toEntity(dto.getDepartmentDTO());
        AcademicYear academicYear = academicYearMapper.toEntity(dto.getAcademicYearDTO());
        AcademicTerm academicTerm = academicTermMapper.toEntity(dto.getAcademicTermDTO());
        Course course = courseMapper.toEntity(dto.getCourseDTO());
        Major major = majorMapper.toEntity(dto.getMajorDTO());
        StudyType studyType = studyTypeMapper.toEntity(dto.getStudyTypeDTO());

        entity.setId(dto.getId());
        entity.setSectionNumber(dto.getSectionNumber());
        entity.setTheoreticalLectures(dto.getTheoreticalLectures());
        entity.setPracticalLectures(dto.getPracticalLectures());
        entity.setExercisesLectures(dto.getExercisesLectures());
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
        if (dto.getCourseDTO() != null) {
            entity.setCourse((Collection<Course>) course);
        }
        if (dto.getMajorDTO() != null) {
            entity.setMajor(major);
        }
        if (dto.getStudyTypeDTO() != null) {
            entity.setStudyType(studyType);
        }

        return entity;
    }

}
