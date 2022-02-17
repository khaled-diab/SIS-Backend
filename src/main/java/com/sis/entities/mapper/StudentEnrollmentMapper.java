package com.sis.entities.mapper;

import com.sis.dto.studentEnrollment.StudentEnrollmentDTO;
import com.sis.entities.*;
import com.sis.util.PageResult;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.Collection;
import static java.util.stream.Collectors.toCollection;

@Component
@AllArgsConstructor
public class StudentEnrollmentMapper implements Mapper<StudentEnrollment, StudentEnrollmentDTO> {

    private CollegeMapper collegeMapper;
    private DepartmentMapper departmentMapper;
    private AcademicYearMapper academicYearMapper;
    private AcademicTermMapper academicTermMapper;
    private StudentMapper studentMapper;
    private CourseMapper courseMapper;
    private SectionMapper sectionMapper;

    @Override
    public ArrayList<StudentEnrollmentDTO> toDTOs(Collection<StudentEnrollment> entities) {
        return entities.stream().map(this::toDTO).collect(toCollection(ArrayList<StudentEnrollmentDTO>::new));
    }

    @Override
    public PageResult<StudentEnrollmentDTO> toDataPage(PageResult<StudentEnrollment> entities) {
        return new PageResult<>(entities.getData().stream().map(this::toDTO).collect(toCollection(ArrayList<StudentEnrollmentDTO>::new)), entities.getTotalCount(), entities.getPageSize(), entities.getCurrPage());
    }

    @Override
    public ArrayList<StudentEnrollment> toEntities(Collection<StudentEnrollmentDTO> dtos) {
        return dtos.stream().map(this::toEntity).collect(toCollection(ArrayList<StudentEnrollment>::new));
    }

    @Override
    public StudentEnrollmentDTO toDTO(StudentEnrollment entity) {
        StudentEnrollmentDTO dto = new StudentEnrollmentDTO();
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
        if (entity.getStudents() != null) {
            dto.setStudentDTO(studentMapper.toDTOs(entity.getStudents()));
        }
        if (entity.getCourses() != null) {
            dto.setCourseDTO(courseMapper.toDTOs(entity.getCourses()));
        }
        if (entity.getSections() != null) {
            dto.setSectionDTO(sectionMapper.toDTOs(entity.getSections()));
        }

        return dto;
    }

    @Override
    public StudentEnrollment toEntity(StudentEnrollmentDTO dto) {
        StudentEnrollment entity = new StudentEnrollment();
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
        if (dto.getStudentDTO() != null) {
            entity.setStudents(studentMapper.toEntities(dto.getStudentDTO()));
        }
        if (dto.getCourseDTO() != null) {
            entity.setCourses(courseMapper.toEntities(dto.getCourseDTO()));
        }
        if (dto.getSectionDTO() != null) {
            entity.setSections(sectionMapper.toEntities(dto.getSectionDTO()));
        }

        return entity;
    }

}
