package com.sis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.sis.dto.LoginDTO;
import com.sis.dto.RegisterDTO;
import com.sis.dto.UserDTO;
import com.sis.entities.User;
import com.sis.service.UserService;

@RestController
@RequestMapping(value = "/api/users")
public class UserController extends BaseController<User, UserDTO> {
	
	@Autowired
	private UserService userService;

	/**
	 * create new user account	 
	 * @param accountDTO
	 * @return user details throws UserNameOrEmailAlreadyExistException
	 */
	@RequestMapping(value = "/CreateAccount", method = RequestMethod.POST)
	public ResponseEntity<UserDTO> createAccount(@RequestBody final RegisterDTO registerDTO) {
		UserDTO userDTO = userService.createAccount(registerDTO);
		return new ResponseEntity<UserDTO>(userDTO, HttpStatus.OK);
	}

	/**
	 * login (username and password)	
	 * @param loginDto
	 * @return user details throws InvalidUserNameOrPasswordException
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<UserDTO> login(@RequestBody final LoginDTO loginDto) {
		UserDTO userDTO = userService.login(loginDto);
		return new ResponseEntity<UserDTO>(userDTO, HttpStatus.OK);
	}
}
