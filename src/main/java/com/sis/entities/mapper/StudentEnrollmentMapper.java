package com.sis.entities.mapper;

import com.sis.dto.AcademicTermDTO;
import com.sis.dto.AcademicYearDTO;
import com.sis.dto.student.StudentDTO;
import com.sis.dto.course.CourseDTO;
import com.sis.dto.section.SectionDTO;
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
        AcademicYearDTO academicYearDTO = academicYearMapper.toDTO(entity.getAcademicYear());
        AcademicTermDTO academicTermDTO = academicTermMapper.toDTO(entity.getAcademicTerm());
        StudentDTO studentDTO = studentMapper.toDTO((Student) entity.getStudent());
        CourseDTO courseDTO = courseMapper.toDTO((Course) entity.getCourse());
        SectionDTO sectionDTO = sectionMapper.toDTO((Section) entity.getSection());

        dto.setId(entity.getId());
        if (entity.getAcademicYear() != null) {
            dto.setAcademicYearDTO(academicYearDTO);
        }
        if (entity.getAcademicTerm() != null) {
            dto.setAcademicTermDTO(academicTermDTO);
        }
        if (entity.getStudent() != null) {
            dto.setStudentDTO(studentDTO);
        }
        if (entity.getCourse() != null) {
            dto.setCourseDTO(courseDTO);
        }
        if (entity.getSection() != null) {
            dto.setSectionDTO(sectionDTO);
        }

        return dto;
    }

    @Override
    public StudentEnrollment toEntity(StudentEnrollmentDTO dto) {
        StudentEnrollment entity = new StudentEnrollment();
        AcademicYear academicYear = academicYearMapper.toEntity(dto.getAcademicYearDTO());
        AcademicTerm academicTerm = academicTermMapper.toEntity(dto.getAcademicTermDTO());
        Student student = studentMapper.toEntity(dto.getStudentDTO());
        Course course = courseMapper.toEntity(dto.getCourseDTO());
        Section section = sectionMapper.toEntity(dto.getSectionDTO());

        entity.setId(dto.getId());
        if (dto.getAcademicYearDTO() != null) {
            entity.setAcademicYear(academicYear);
        }
        if (dto.getAcademicTermDTO() != null) {
            entity.setAcademicTerm(academicTerm);
        }
        if (dto.getStudentDTO() != null) {
            entity.setStudent((Collection<Student>) student);
        }
        if (dto.getCourseDTO() != null) {
            entity.setCourse((Collection<Course>) course);
        }
        if (dto.getSectionDTO() != null) {
            entity.setSection((Collection<Section>) section);
        }

        return entity;
    }

}
