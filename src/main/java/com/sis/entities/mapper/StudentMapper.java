package com.sis.entities.mapper;


import com.sis.dto.student.StudentDTO;

import com.sis.entities.Student;
import com.sis.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

import static java.util.stream.Collectors.toCollection;

@Component
public class StudentMapper implements Mapper<Student,StudentDTO> {



	@Autowired
	private  DepartmentMapper departmentMapper;

	@Autowired
	private CollegeMapper collegeMapper;

	@Autowired
	private AcademicProgramMapper academicProgramMapper;


	@Override
	public StudentDTO toDTO(Student entity) {

		StudentDTO dto = new StudentDTO();
		dto.setAlternativeMail(entity.getAlternativeMail());
		dto.setUniversityMail(entity.getUniversityMail());
		dto.setBirthDate(entity.getBirthDate());
		dto.setId(entity.getId());
		dto.setNameEn(entity.getNameEn());
		dto.setNationality(entity.getNationality());
		dto.setLevel(entity.getLevel());
		dto.setYear(entity.getYear());
		dto.setNameAr(entity.getNameAr());
		dto.setNationalId(entity.getNationalId());
		dto.setPhone(entity.getPhone());
		dto.setParentPhone(entity.getParentPhone());
		dto.setPhoto(entity.getPhoto());
		dto.setUniversityId(entity.getUniversityId());
		if(entity.getDepartmentId()!=null) {
			dto.setDepartmentDTO(this.departmentMapper.toDTO(entity.getDepartmentId()));

		}
		if(entity.getCollegeId()!=null) {
			dto.setCollegeDTO(this.collegeMapper.toDTO(entity.getCollegeId()));
		}
		if(entity.getProgramId()!=null) {
			dto.setAcademicProgramDTO(this.academicProgramMapper.toDTO(entity.getProgramId()));
		}



		return dto;
	}

	@Override
	public Student toEntity(StudentDTO dto) {

		Student entity=new Student();
		if(dto != null) {
			entity.setId(dto.getId());
			entity.setAlternativeMail(dto.getAlternativeMail());
			entity.setLevel(dto.getLevel());
			entity.setYear(dto.getYear());
			entity.setBirthDate(dto.getBirthDate());
			entity.setNationality(dto.getNationality());
			entity.setNameEn(dto.getNameEn());
			entity.setNameAr(dto.getNameAr());
			entity.setParentPhone(dto.getParentPhone());
			entity.setPhone(dto.getPhone());
			entity.setPhoto(dto.getPhoto());
			System.out.println(dto.getUniversityId());
			entity.setUniversityId(dto.getUniversityId());
			entity.setNationalId(dto.getNationalId());
			entity.setUniversityMail(dto.getUniversityMail());
			if (dto.getCollegeDTO() != null) {
				entity.setCollegeId(this.collegeMapper.toEntity(dto.getCollegeDTO()));
			}
			if (dto.getDepartmentDTO() != null) {

				entity.setDepartmentId(this.departmentMapper.toEntity(dto.getDepartmentDTO()));

			}
			if (dto.getAcademicProgramDTO() != null) {

				entity.setProgramId(this.academicProgramMapper.toEntity(dto.getAcademicProgramDTO()));
			}
		}
		return entity;

	}

	@Override
	public ArrayList<StudentDTO> toDTOs(Collection<Student> students) {
		return students.stream().map(entity -> toDTO(entity)).collect(toCollection(ArrayList<StudentDTO>::new));
	}

	@Override
	public ArrayList<Student> toEntities(Collection<StudentDTO> studentDTOS) {
		return studentDTOS.stream().map(dto -> toEntity(dto)).collect(toCollection(ArrayList<Student>::new));
	}

	@Override
	public PageResult<StudentDTO> toDataPage(PageResult<Student> entities) {
		return new PageResult<>(entities.getData().stream().map(entity -> toDTO(entity)).collect(toCollection(ArrayList<StudentDTO>::new)), entities.getTotalCount(), entities.getPageSize(), entities.getCurrPage());

	}
}
