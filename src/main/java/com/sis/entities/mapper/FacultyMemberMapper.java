package com.sis.entities.mapper;

import static java.util.stream.Collectors.toCollection;
import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.sis.dto.FacultyMemberDTO;
import com.sis.entities.FacultyMember;
import com.sis.util.PageResult;

@Component
public class FacultyMemberMapper implements Mapper<FacultyMember, FacultyMemberDTO> {

	@Autowired
	private CollegeMapper collegeMapper;

	@Autowired
	private DepartmentMapper departmentMapper;

	@Override
	public ArrayList<FacultyMemberDTO> toDTOs(Collection<FacultyMember> entities) {
		return entities.stream().map(entity -> toDTO(entity)).collect(toCollection(ArrayList<FacultyMemberDTO>::new));
	}
	@Override
	public PageResult<FacultyMemberDTO> toDataPage(PageResult<FacultyMember> entities) {
		return new PageResult<>(entities.getData().stream().map(entity -> toDTO(entity)).collect(toCollection(ArrayList<FacultyMemberDTO>::new)), entities.getTotalCount(), entities.getPageSize(), entities.getCurrPage());
	}
	@Override
	public ArrayList<FacultyMember> toEntities(Collection<FacultyMemberDTO> dtos) {
		return dtos.stream().map(dto -> toEntity(dto)).collect(toCollection(ArrayList<FacultyMember>::new));
	}
	@Override
	public FacultyMemberDTO toDTO(FacultyMember entity) {
		FacultyMemberDTO dto=new FacultyMemberDTO();
		dto.setId(entity.getId());
		dto.setUniversityMail(entity.getUniversityMail());
		dto.setAlternativeMail(entity.getAlternativeMail());
		dto.setBirthDate(entity.getBirthDate());
		dto.setDegree(entity.getDegree());
		dto.setNameAr(entity.getNameAr());
		dto.setNameEn(entity.getNameEn());
		dto.setNationalID(entity.getNationalID());
		dto.setNationality(entity.getNationality());
		dto.setPhone(entity.getPhone());
		if(entity.getDepartmentId()!=null) {
			dto.setDepartmentDTO(this.departmentMapper.toDTO(entity.getDepartmentId()));
		}
		if(entity.getCollegeId()!=null) {
			dto.setCollageDTO(this.collegeMapper.toDTO(entity.getCollegeId()));
		}

		return dto;
	}
	@Override
	public FacultyMember toEntity(FacultyMemberDTO dto) {
		FacultyMember entity=new FacultyMember();
		entity.setId(dto.getId());
		entity.setUniversityMail(dto.getUniversityMail());
		entity.setAlternativeMail(dto.getAlternativeMail());
		entity.setBirthDate(dto.getBirthDate());
		entity.setDegree(dto.getDegree());
		entity.setNameAr(dto.getNameAr());
		entity.setNameEn(dto.getNameEn());
		entity.setNationalID(dto.getNationalID());
		entity.setNationality(dto.getNationality());
		entity.setPhone(dto.getPhone());
		if(dto.getCollegeDTO()!=null) {
			entity.setCollegeId(this.collegeMapper.toEntity(dto.getCollegeDTO()));
		}
		if(dto.getDepartmentDTO()!=null) {

			entity.setDepartmentId(this.departmentMapper.toEntity(dto.getDepartmentDTO()));
		}

		return entity;
	}

}
