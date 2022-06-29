package com.sis.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.sis.dto.college.CollegeDTO;


public abstract class BaseDTO {
	private long id;
	public BaseDTO(){

	}
	public BaseDTO(long id){

	}
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
}
