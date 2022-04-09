package com.sis.entity.mapper;

import com.sis.dto.studentEnrollment.StudentEnrollmentDTO;
import com.sis.entity.StudentEnrollment;
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
    private MajorMapper majorMapper;
    private StudyTypeMapper studyTypeMapper;

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
        if (entity.getStudent() != null) {
            dto.setStudentDTO(studentMapper.toDTO(entity.getStudent()));
        }
        if (entity.getCourse() != null) {
            dto.setCourseDTO(courseMapper.toDTO(entity.getCourse()));
        }
        if (entity.getSection() != null) {
            dto.setSectionDTO(sectionMapper.toDTO(entity.getSection()));
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
            entity.setStudent(studentMapper.toEntity(dto.getStudentDTO()));
        }
        if (dto.getCourseDTO() != null) {
            entity.setCourse(courseMapper.toEntity(dto.getCourseDTO()));
        }
        if (dto.getSectionDTO() != null) {
            entity.setSection(sectionMapper.toEntity(dto.getSectionDTO()));
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
