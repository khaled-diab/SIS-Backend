package com.sis.entities.mapper;

import com.sis.dto.*;
import com.sis.dto.FacultyMemberEnrollment.FacultyMemberEnrollmentDTO;
import com.sis.dto.course.CourseDTO;
import com.sis.dto.facultyMember.FacultyMemberDTO;
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
public class FacultyMemberEnrollmentMapper implements Mapper<FacultyMemberEnrollment, FacultyMemberEnrollmentDTO> {

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
        AcademicYearDTO academicYearDTO = academicYearMapper.toDTO(entity.getAcademicYear());
        AcademicTermDTO academicTermDTO = academicTermMapper.toDTO(entity.getAcademicTerm());
        FacultyMemberDTO facultyMemberDTO = facultyMemberMapper.toDTO((FacultyMember) entity.getFacultyMember());
        CourseDTO courseDTO = courseMapper.toDTO((Course) entity.getCourse());
        SectionDTO sectionDTO = sectionMapper.toDTO((Section) entity.getSection());

        dto.setId(entity.getId());
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

        return dto;
    }

    @Override
    public FacultyMemberEnrollment toEntity(FacultyMemberEnrollmentDTO dto) {
        FacultyMemberEnrollment entity = new FacultyMemberEnrollment();
        AcademicYear academicYear = academicYearMapper.toEntity(dto.getAcademicYearDTO());
        AcademicTerm academicTerm = academicTermMapper.toEntity(dto.getAcademicTermDTO());
        FacultyMember facultyMember = facultyMemberMapper.toEntity(dto.getFacultyMemberDTO());
        Course course = courseMapper.toEntity(dto.getCourseDTO());
        Section section = sectionMapper.toEntity(dto.getSectionDTO());

        entity.setId(dto.getId());
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

        return entity;
    }

}
