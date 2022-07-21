package com.sis.entity.mapper;

import com.sis.dto.gradeBook.GradeBookDTO;
import com.sis.entity.GradeBook;
import com.sis.util.PageResult;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

import static java.util.stream.Collectors.toCollection;

@Component
@AllArgsConstructor
public class GradeBookMapper implements Mapper<GradeBook, GradeBookDTO> {
    private AcademicTermMapper academicTermMapper;
    private StudentMapper studentMapper;
    private CourseMapper courseMapper;
    private FacultyMemberMapper facultyMemberMapper;

    @Override
    public ArrayList<GradeBookDTO> toDTOs(Collection<GradeBook> entity) {
        return entity.stream().map(this::toDTO).collect(toCollection(ArrayList<GradeBookDTO>::new));
    }

    @Override
    public PageResult<GradeBookDTO> toDataPage(PageResult<GradeBook> entity) {
        return new PageResult<>(entity.getData().stream().map(this::toDTO).collect(toCollection(ArrayList<GradeBookDTO>::new)), entity.getTotalCount(), entity.getPageSize(), entity.getCurrPage());
    }

    @Override
    public ArrayList<GradeBook> toEntities(Collection<GradeBookDTO> dtos) {
        return dtos.stream().map(this::toEntity).collect(toCollection(ArrayList<GradeBook>::new));
    }

    @Override
    public GradeBookDTO toDTO(GradeBook entity) {
        GradeBookDTO dto = new GradeBookDTO();
        dto.setId(entity.getId());
        if (entity.getAcademicTerm() != null) {
            dto.setAcademicTermDTO(academicTermMapper.toDTO(entity.getAcademicTerm()));
        }
        if (entity.getStudent() != null) {
            dto.setStudentDTO(studentMapper.toDTO(entity.getStudent()));
        }
        if (entity.getCourse() != null) {
            dto.setCourseDTO(courseMapper.toDTO(entity.getCourse()));
        }
//        if (entity.getFacultyMember() != null) {
//            dto.setFacultyMemberDTO(facultyMemberMapper.toDTO(entity.getFacultyMember()));
//        }
        dto.setFinalExamGrade(entity.getFinalExamGrade());
        dto.setPracticalGrade(entity.getPracticalGrade());
        dto.setOralGrade(entity.getOralGrade());
        dto.setMidGrade(entity.getMidGrade());
        return dto;
    }

    @Override
    public GradeBook toEntity(GradeBookDTO dto) {
        GradeBook entity = new GradeBook();
        entity.setId(dto.getId());
        if (dto.getAcademicTermDTO() != null) {
            entity.setAcademicTerm(academicTermMapper.toEntity(dto.getAcademicTermDTO()));
        }
        if (dto.getStudentDTO() != null) {
            entity.setStudent(studentMapper.toEntity(dto.getStudentDTO()));
        }
        if (dto.getCourseDTO() != null) {
            entity.setCourse(courseMapper.toEntity(dto.getCourseDTO()));
        }
//        if (dto.getFacultyMemberDTO() != null) {
//            entity.setFacultyMember(facultyMemberMapper.toEntity(dto.getFacultyMemberDTO()));
//        }
        entity.setFinalExamGrade(dto.getFinalExamGrade());
        entity.setPracticalGrade(dto.getPracticalGrade());
        entity.setOralGrade(dto.getOralGrade());
        entity.setMidGrade(dto.getMidGrade());
        return entity;
    }

}
