package com.sis.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.EXPECTATION_FAILED)
public class InvalidUserNameOrPasswordException extends RuntimeException{
	
	private static final long serialVersionUID = 316948179517070191L;

	public InvalidUserNameOrPasswordException() {
		super("Username or Password is incorrect");
	}	

}
