package com.sis.entities.mapper;

import static java.util.stream.Collectors.toCollection;
import java.util.ArrayList;
import java.util.Collection;

import com.sis.dto.DepartmentDTO;
import com.sis.dto.college.CollegeDTO;
import com.sis.dto.course.CourseDTO;
import com.sis.entities.Course;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import com.sis.util.PageResult;

@Component
@AllArgsConstructor
public class CourseMapper implements Mapper<Course, CourseDTO> {

	CollegeMapper collegeMapper;
	DepartmentMapper departmentMapper;


	@Override
	public ArrayList<CourseDTO> toDTOs(Collection<Course> entities) {
		return entities.stream().map(this::toDTO).collect(toCollection(ArrayList<CourseDTO>::new));
	}
	@Override
	public PageResult<CourseDTO> toDataPage(PageResult<Course> entities) {
		return new PageResult<>(entities.getData().stream().map(this::toDTO).collect(toCollection(ArrayList<CourseDTO>::new)), entities.getTotalCount(), entities.getPageSize(), entities.getCurrPage());
	}
	@Override
	public ArrayList<Course> toEntities(Collection<CourseDTO> dtos) {
		return dtos.stream().map(this::toEntity).collect(toCollection(ArrayList<Course>::new));
	}
	@Override
	public CourseDTO toDTO(Course entity) {
		CollegeDTO collegeDTO = collegeMapper.toDTO(entity.getCollege());
		DepartmentDTO departmentDTO = departmentMapper.toDTO(entity.getDepartment());
//		collegeDTO.setCourseList(null);
		CourseDTO dto=new CourseDTO();
		dto.setId(entity.getId());
		dto.setCode(entity.getCode());
		dto.setNameAr(entity.getNameAr());
		dto.setNameEn(entity.getNameEn());
		dto.setTheoreticalHours(entity.getTheoreticalHours());
		dto.setExercisesHours(entity.getExercisesHours());
		dto.setPracticalHours(entity.getPracticalHours());
		dto.setTotalHours(entity.getTotalHours());
		dto.setWeeks(entity.getWeeks());
		dto.setFinalGrade(entity.getFinalGrade());
		dto.setFinalExamGrade(entity.getFinalExamGrade());
		dto.setPracticalGrade(entity.getPracticalGrade());
		dto.setOralGrade(entity.getOralGrade());
		dto.setMidGrade(entity.getMidGrade());
		dto.setCollegeDTO(collegeDTO);
		dto.setDepartmentDTO(departmentDTO);

		return dto;
	}
	@Override
	public Course toEntity(CourseDTO dto) {
		Course entity=new Course();
		CollegeMapper collegeMapper = new CollegeMapper();
		entity.setId(dto.getId());
		entity.setCode(dto.getCode());
		entity.setNameAr(dto.getNameAr());
		entity.setNameEn(dto.getNameEn());
		entity.setTheoreticalHours(dto.getTheoreticalHours());
		entity.setExercisesHours(dto.getExercisesHours());
		entity.setPracticalHours(dto.getPracticalHours());
		entity.setTotalHours(dto.getTotalHours());
		entity.setWeeks(dto.getWeeks());
		entity.setFinalGrade(dto.getFinalGrade());
		entity.setFinalExamGrade(dto.getFinalExamGrade());
		entity.setPracticalGrade(dto.getPracticalGrade());
		entity.setOralGrade(dto.getOralGrade());
		entity.setMidGrade(dto.getMidGrade());
		entity.setCollege(collegeMapper.toEntity(dto.getCollegeDTO()));
		entity.setDepartment(departmentMapper.toEntity(dto.getDepartmentDTO()));
		return entity;
	}

}
