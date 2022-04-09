package com.sis.entity.mapper;

import com.sis.dto.StudyTypeDTO;
import com.sis.entity.StudyType;
import com.sis.util.PageResult;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

import static java.util.stream.Collectors.toCollection;

@Component
@AllArgsConstructor
public class StudyTypeMapper implements Mapper<StudyType, StudyTypeDTO> {

	@Override
	public ArrayList<StudyTypeDTO> toDTOs(Collection<StudyType> entities) {
		return entities.stream().map(this::toDTO).collect(toCollection(ArrayList<StudyTypeDTO>::new));
	}
	@Override
	public PageResult<StudyTypeDTO> toDataPage(PageResult<StudyType> entities) {
		return new PageResult<>(entities.getData().stream().map(this::toDTO).collect(toCollection(ArrayList<StudyTypeDTO>::new)), entities.getTotalCount(), entities.getPageSize(), entities.getCurrPage());
	}
	@Override
	public ArrayList<StudyType> toEntities(Collection<StudyTypeDTO> dtos) {
		return dtos.stream().map(this::toEntity).collect(toCollection(ArrayList<StudyType>::new));
	}
	@Override
	public StudyTypeDTO toDTO(StudyType entity) {
		StudyTypeDTO dto=new StudyTypeDTO();
		dto.setId(entity.getId());
		dto.setNameAr(entity.getNameAr());
		dto.setNameEn(entity.getNameEn());

		return dto;
	}
	@Override
	public StudyType toEntity(StudyTypeDTO dto) {
		StudyType entity=new StudyType();
		entity.setId(dto.getId());
		entity.setNameAr(dto.getNameAr());
		entity.setNameEn(dto.getNameEn());

		return entity;
	}

}
