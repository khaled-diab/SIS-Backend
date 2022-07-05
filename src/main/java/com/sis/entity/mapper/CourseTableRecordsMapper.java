package com.sis.entity.mapper;

import com.sis.dto.course.CourseTableRecordsDTO;
import com.sis.entity.Course;
import com.sis.util.PageResult;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

import static java.util.stream.Collectors.toCollection;

@Component
@AllArgsConstructor
public class CourseTableRecordsMapper implements Mapper<Course, CourseTableRecordsDTO> {

    @Override
    public ArrayList<CourseTableRecordsDTO> toDTOs(Collection<Course> entity) {
        return entity.stream().map(this::toDTO).collect(toCollection(ArrayList<CourseTableRecordsDTO>::new));
    }

    @Override
    public PageResult<CourseTableRecordsDTO> toDataPage(PageResult<Course> entity) {
        return new PageResult<>(entity.getData().stream().map(this::toDTO).collect(toCollection(ArrayList<CourseTableRecordsDTO>::new)), entity.getTotalCount(), entity.getPageSize(), entity.getCurrPage());
    }

    @Override
    public ArrayList<Course> toEntities(Collection<CourseTableRecordsDTO> dtos) {
        return dtos.stream().map(this::toEntity).collect(toCollection(ArrayList<Course>::new));
    }

    @Override
    public CourseTableRecordsDTO toDTO(Course entity) {
        CourseTableRecordsDTO dto = new CourseTableRecordsDTO();
        dto.setId(entity.getId());
        dto.setCode(entity.getCode());
        dto.setNameAr(entity.getNameAr());
        dto.setNameEn(entity.getNameEn());
        dto.setFinalGrade(entity.getFinalGrade());
        dto.setCollegeName(entity.getCollege().getNameEn());
        dto.setDepartmentName(entity.getDepartment().getNameEn());
        return dto;
    }

	@Override
	public Course toEntity(CourseTableRecordsDTO courseTableRecordsDTO) {
		return null;
	}

}
