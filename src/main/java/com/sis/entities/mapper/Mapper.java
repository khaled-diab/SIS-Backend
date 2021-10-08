package com.sis.entities.mapper;

import java.util.ArrayList;
import java.util.Collection;
import org.springframework.stereotype.Component;
import com.sis.dto.BaseDTO;
import com.sis.entities.BaseEntity;
import com.sis.util.PageResult;

@Component
public interface Mapper <Entity extends BaseEntity , DTO extends BaseDTO> {
	DTO toDTO(final Entity entity);
	Entity toEntity(final DTO dto);
	ArrayList<DTO> toDTOs(final Collection<Entity> entities);
	ArrayList<Entity> toEntities(final Collection<DTO> dtos);
	PageResult<DTO> toDataPage(final PageResult<Entity> entities);	
}
