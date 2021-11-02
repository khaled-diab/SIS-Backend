package com.sis.entities.mapper;

import static java.util.stream.Collectors.toCollection;
import java.util.ArrayList;
import java.util.Collection;

import com.sis.entities.FacultyMember;
import org.springframework.stereotype.Component;
import com.sis.dto.FacultyDTO;
import com.sis.util.PageResult;

@Component
public class FacultyMapper implements Mapper<FacultyMember, FacultyDTO> {

	@Override
	public ArrayList<FacultyDTO> toDTOs(Collection<FacultyMember> entities) {
		return entities.stream().map(entity -> toDTO(entity)).collect(toCollection(ArrayList<FacultyDTO>::new));
	}
	@Override
	public PageResult<FacultyDTO> toDataPage(PageResult<FacultyMember> entities) {
		return new PageResult<>(entities.getData().stream().map(entity -> toDTO(entity)).collect(toCollection(ArrayList<FacultyDTO>::new)), entities.getTotalCount(), entities.getPageSize(), entities.getCurrPage());
	}
	@Override
	public ArrayList<FacultyMember> toEntities(Collection<FacultyDTO> dtos) {
		return dtos.stream().map(dto -> toEntity(dto)).collect(toCollection(ArrayList<FacultyMember>::new));
	}
	@Override
	public FacultyDTO toDTO(FacultyMember entity) {
		FacultyDTO dto=new FacultyDTO();
		dto.setId(entity.getId());
		dto.setAlternativeMail(entity.getAlternativeMail());
		dto.setBirthDate(entity.getBirthDate());
		dto.setDegree(entity.getDegree());
		dto.setNameAr(entity.getNameAr());
		dto.setNameEn(entity.getNameEn());
		dto.setNationalID(entity.getNationalID());
		dto.setNationality(entity.getNationality());
		dto.setPhone(entity.getPhone());	
		return dto;
	}
	@Override
	public FacultyMember toEntity(FacultyDTO dto) {
		FacultyMember entity=new FacultyMember();
		entity.setAlternativeMail(dto.getAlternativeMail());
		entity.setBirthDate(dto.getBirthDate());
		entity.setDegree(dto.getDegree());
		entity.setNameAr(dto.getNameAr());
		entity.setNameEn(dto.getNameEn());
		entity.setNationalID(dto.getNationalID());
		entity.setNationality(dto.getNationality());
		entity.setPhone(dto.getPhone());
		return entity;
	}

}
