package com.sis.dto;

import javax.validation.constraints.Email;

import org.springframework.lang.NonNull;
import org.springframework.validation.annotation.Validated;

@Validated
public class RegisterDTO {

	@NonNull
	private String firstname;
	@NonNull
	private String lastname;	
	@NonNull
	private String username;	
	@Email
	private String email;
	@NonNull
	private String password;
	
	
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
