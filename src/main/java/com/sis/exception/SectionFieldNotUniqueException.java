package com.sis.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.EXPECTATION_FAILED)
public class SectionFieldNotUniqueException extends RuntimeException{
	private static final long serialVersionUID = 2426256104172639927L;
	private String field;
	public SectionFieldNotUniqueException(String field, String Message )
	{
		super(Message);
		this.field=field;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}
}
