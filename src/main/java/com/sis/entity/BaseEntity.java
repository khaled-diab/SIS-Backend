package com.sis.entity;

import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@MappedSuperclass
@NoArgsConstructor
public class BaseEntity implements Serializable  {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false) 
	@Column(name = "id", nullable = false, columnDefinition = "BIGINT")
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}	
	
}
