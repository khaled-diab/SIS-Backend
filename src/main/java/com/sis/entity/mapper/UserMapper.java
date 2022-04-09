package com.sis.entity.mapper;

import com.sis.dto.security.UserDto;
import com.sis.entity.security.User;
import com.sis.util.PageResult;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

import static java.util.stream.Collectors.toCollection;

@Component
public class UserMapper implements Mapper<User, UserDto> {
	@Override
	public ArrayList<UserDto> toDTOs(final Collection<User> entities) {
		return entities.stream().map(this::toDTO).collect(toCollection(ArrayList<UserDto>::new));
	}

	@Override
	public PageResult<UserDto> toDataPage(PageResult<User> entities) {
		return new PageResult<>(entities.getData().stream().map(this::toDTO).collect(toCollection(ArrayList<UserDto>::new)), entities.getTotalCount(), entities.getPageSize(), entities.getCurrPage());

	}

	@Override
	public ArrayList<User> toEntities(Collection<UserDto> dtoCollection) {
		return dtoCollection.stream().map(this::toEntity).collect(toCollection(ArrayList<User>::new));
	}

	@Override
	public UserDto toDTO(final User entity) {
		UserDto dto = new UserDto();
		dto.setId(entity.getId());
		dto.setFirstname(entity.getFirstname());
		dto.setLastname(entity.getLastname());
		dto.setEmail(entity.getEmail());
		dto.setUsername(entity.getUsername());
		dto.setToken(entity.getToken());
		dto.setRole(entity.getRole());
		dto.setType(entity.getType());
		return dto;
	}

	@Override
	public User toEntity(UserDto dto) {
		User entity = new User();
		entity.setUsername(dto.getUsername());
		entity.setToken(dto.getToken());
		entity.setRole(dto.getRole());
		entity.setType(dto.getType());
		entity.setId(dto.getId());
		entity.setFirstname(dto.getFirstname());
		entity.setLastname(dto.getLastname());
		entity.setEmail(dto.getEmail());
		return entity;
	}
	
}
