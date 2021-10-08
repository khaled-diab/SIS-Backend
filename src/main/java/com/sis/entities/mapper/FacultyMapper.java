package com.sis.entities.mapper;

import static java.util.stream.Collectors.toCollection;
import java.util.ArrayList;
import java.util.Collection;
import org.springframework.stereotype.Component;
import com.sis.dto.FacultyDTO;
import com.sis.entities.Faculty;
import com.sis.util.PageResult;

@Component
public class FacultyMapper implements Mapper<Faculty, FacultyDTO> {

	@Override
	public ArrayList<FacultyDTO> toDTOs(Collection<Faculty> entities) {
		return entities.stream().map(entity -> toDTO(entity)).collect(toCollection(ArrayList<FacultyDTO>::new));
	}
	@Override
	public PageResult<FacultyDTO> toDataPage(PageResult<Faculty> entities) {
		return new PageResult<>(entities.getData().stream().map(entity -> toDTO(entity)).collect(toCollection(ArrayList<FacultyDTO>::new)), entities.getTotalCount(), entities.getPageSize(), entities.getCurrPage());
	}
	@Override
	public ArrayList<Faculty> toEntities(Collection<FacultyDTO> dtos) {
		return dtos.stream().map(dto -> toEntity(dto)).collect(toCollection(ArrayList<Faculty>::new));		
	}
	@Override
	public FacultyDTO toDTO(Faculty entity) {
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
	public Faculty toEntity(FacultyDTO dto) {
		Faculty entity=new Faculty();
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
