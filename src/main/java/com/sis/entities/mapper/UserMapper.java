package com.sis.entities.mapper;

import static java.util.stream.Collectors.toCollection;
import java.util.ArrayList;
import java.util.Collection;
import org.springframework.stereotype.Component;
import com.sis.dto.UserDTO;
import com.sis.entities.security.User;
import com.sis.util.PageResult;

@Component
public class UserMapper implements Mapper<User,UserDTO> {
	@Override
	public ArrayList<UserDTO> toDTOs(final Collection<User> entities) {
		return entities.stream().map(entity -> toDTO(entity)).collect(toCollection(ArrayList<UserDTO>::new));
	}
	@Override
	public PageResult<UserDTO> toDataPage(PageResult<User> entities) {
		return new PageResult<>(entities.getData().stream().map(entity -> toDTO(entity)).collect(toCollection(ArrayList<UserDTO>::new)), entities.getTotalCount(), entities.getPageSize(), entities.getCurrPage());
		
	}
	@Override
	public ArrayList<User> toEntities(Collection<UserDTO> dtos) {
		return dtos.stream().map(dto -> toEntity(dto)).collect(toCollection(ArrayList<User>::new));
	}	
	@Override
	public UserDTO toDTO(final User entity)
	{
		UserDTO dto	=new UserDTO();
		dto.setId(entity.getId());
		dto.setId(entity.getId());
		dto.setFirstname(entity.getFirstname());
		dto.setLastname(entity.getLastname());
		dto.setEmail(entity.getEmail());
		dto.setUsername(entity.getUsername());
		return dto;
	}
	@Override
	public User toEntity(UserDTO dto) {
		User entity=new User();
		entity.setId(dto.getId());
		entity.setFirstname(dto.getFirstname());
		entity.setLastname(dto.getLastname());
		entity.setEmail(dto.getEmail());
		entity.setUsername(dto.getUsername());
		return entity;
	}
	
}
