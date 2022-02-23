package com.sis.entities.mapper;

import com.sis.dto.FacultyMemberEnrollment.FacultyMemberEnrollmentDTO;
import com.sis.entities.*;
import com.sis.util.PageResult;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

import static java.util.stream.Collectors.toCollection;

@Component
@AllArgsConstructor
public class FacultyMemberEnrollmentMapper implements Mapper<FacultyMemberEnrollment, FacultyMemberEnrollmentDTO> {

    private CollegeMapper collegeMapper;
    private DepartmentMapper departmentMapper;
    private AcademicYearMapper academicYearMapper;
    private AcademicTermMapper academicTermMapper;
    private FacultyMemberMapper facultyMemberMapper;
    private CourseMapper courseMapper;
    private SectionMapper sectionMapper;

    @Override
    public ArrayList<FacultyMemberEnrollmentDTO> toDTOs(Collection<FacultyMemberEnrollment> entities) {
        return entities.stream().map(this::toDTO).collect(toCollection(ArrayList<FacultyMemberEnrollmentDTO>::new));
    }

    @Override
    public PageResult<FacultyMemberEnrollmentDTO> toDataPage(PageResult<FacultyMemberEnrollment> entities) {
        return new PageResult<>(entities.getData().stream().map(this::toDTO).collect(toCollection(ArrayList<FacultyMemberEnrollmentDTO>::new)), entities.getTotalCount(), entities.getPageSize(), entities.getCurrPage());
    }

    @Override
    public ArrayList<FacultyMemberEnrollment> toEntities(Collection<FacultyMemberEnrollmentDTO> dtos) {
        return dtos.stream().map(this::toEntity).collect(toCollection(ArrayList<FacultyMemberEnrollment>::new));
    }

    @Override
    public FacultyMemberEnrollmentDTO toDTO(FacultyMemberEnrollment entity) {
        FacultyMemberEnrollmentDTO dto = new FacultyMemberEnrollmentDTO();
        dto.setId(entity.getId());
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

        return dto;
    }

    @Override
    public FacultyMemberEnrollment toEntity(FacultyMemberEnrollmentDTO dto) {
        FacultyMemberEnrollment entity = new FacultyMemberEnrollment();
        entity.setId(dto.getId());
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

        return entity;
    }

}
