package com.sis.entity.mapper;

import com.sis.dto.BaseDTO;
import com.sis.entity.BaseEntity;
import com.sis.util.PageResult;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

@Component
public interface Mapper <Entity extends BaseEntity , DTO extends BaseDTO> {
	DTO toDTO(final Entity entity);
	Entity toEntity(final DTO dto);
	ArrayList<DTO> toDTOs(final Collection<Entity> entities);
	ArrayList<Entity> toEntities(final Collection<DTO> dtos);
	PageResult<DTO> toDataPage(final PageResult<Entity> entities);	
}
