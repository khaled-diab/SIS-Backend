package com.sis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.sis.dao.UserRepository;
import com.sis.dto.LoginDTO;
import com.sis.dto.RegisterDTO;
import com.sis.dto.UserDTO;
import com.sis.entities.security.User;
import com.sis.entities.mapper.UserMapper;
import com.sis.exception.InvalidUserNameOrPasswordException;
import com.sis.exception.UserNameOrEmailAlreadyExistException;

@Service
public class UserService  extends BaseServiceImp<User>{

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public JpaRepository<User, Long> Repository() {
		return userRepository;
	}
	/**
	 * Create new user account
	 * 
	 * @param accountDTO (username , email , password)
	 * @return
	 * @throws UserNameOrEmailAlreadyExistException
	 */
	public UserDTO createAccount(final RegisterDTO accountDTO) {

		if (userRepository.findByEmailOrUsername(accountDTO.getEmail(), accountDTO.getUsername()) != null)
			throw new UserNameOrEmailAlreadyExistException(accountDTO.getUsername(),accountDTO.getPassword());

		//map accountDTO to User
		final User user = new User();
		user.setFirstname(accountDTO.getFirstname());
		user.setLastname(accountDTO.getLastname());
		user.setEmail(accountDTO.getEmail());
		user.setPassword(passwordEncoder.encode(accountDTO.getPassword()));
		user.setUsername(accountDTO.getUsername());		
		//Save user
		return userMapper.toDTO(userRepository.save(user));
	}
    /**
     * login
     * @param loginDTO (username , password )
     * @return
     * @throws InvalidUserNameOrPasswordException
     */
	public UserDTO login(final LoginDTO loginDTO) {		
		User user = userRepository.findByUsername(loginDTO.getUsername()).orElseThrow(InvalidUserNameOrPasswordException::new);
		boolean isPasswordMatch = passwordEncoder.matches(loginDTO.getPassword(), user.getPassword());
		if (!isPasswordMatch)
			throw new InvalidUserNameOrPasswordException();
		return userMapper.toDTO(user);
	}

}
