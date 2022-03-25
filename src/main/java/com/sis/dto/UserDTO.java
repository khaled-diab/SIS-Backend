package com.sis.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.AccessType;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO extends BaseDTO{
	
	private String firstname;
	private String lastname;
	private String username;
	private String email;


}
