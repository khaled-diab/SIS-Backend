package com.sis.entity.mapper;

import com.sis.dto.gradeBook.GradeBookStudentRecordsDTO;
import com.sis.entity.GradeBook;
import com.sis.util.PageResult;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

import static java.util.stream.Collectors.toCollection;

@Component
@AllArgsConstructor
public class GradeBookStudentRecordsMapper implements Mapper<GradeBook, GradeBookStudentRecordsDTO> {

    @Override
    public ArrayList<GradeBookStudentRecordsDTO> toDTOs(Collection<GradeBook> entity) {
        return entity.stream().map(this::toDTO).collect(toCollection(ArrayList<GradeBookStudentRecordsDTO>::new));
    }

    @Override
    public PageResult<GradeBookStudentRecordsDTO> toDataPage(PageResult<GradeBook> entity) {
        return new PageResult<>(entity.getData().stream().map(this::toDTO).collect(toCollection(ArrayList<GradeBookStudentRecordsDTO>::new)), entity.getTotalCount(), entity.getPageSize(), entity.getCurrPage());
    }

    @Override
    public ArrayList<GradeBook> toEntities(Collection<GradeBookStudentRecordsDTO> dtos) {
        return dtos.stream().map(this::toEntity).collect(toCollection(ArrayList<GradeBook>::new));
    }

    @Override
    public GradeBookStudentRecordsDTO toDTO(GradeBook entity) {
        GradeBookStudentRecordsDTO dto = new GradeBookStudentRecordsDTO();
        dto.setId(entity.getId());
        dto.setStudentNameEn(entity.getStudent().getNameEn());
        dto.setStudentNameAr(entity.getStudent().getNameAr());
        dto.setStudentUniversityId(String.valueOf(entity.getStudent().getUniversityId()));
        dto.setCourseNameEn(entity.getSection().getCourse().getNameEn());
        dto.setCourseNameAr(entity.getSection().getCourse().getNameAr());
        dto.setCourseFinalExamGrade(Double.valueOf(entity.getSection().getCourse().getFinalExamGrade()));
        dto.setCourseMidGrade(Double.valueOf(entity.getSection().getCourse().getMidGrade()));
        dto.setCoursePracticalGrade(Double.valueOf(entity.getSection().getCourse().getPracticalGrade()));
        dto.setCourseOralGrade(Double.valueOf(entity.getSection().getCourse().getOralGrade()));
        dto.setCourseTotalGrade(Double.valueOf(entity.getSection().getCourse().getFinalGrade()));
        dto.setFinalExamGrade(entity.getFinalExamGrade());
        dto.setPracticalGrade(entity.getPracticalGrade());
        dto.setOralGrade(entity.getOralGrade());
        dto.setMidGrade(entity.getMidGrade());
        return dto;
    }

    @Override
    public GradeBook toEntity(GradeBookStudentRecordsDTO dto) {
        return null;
    }

}
