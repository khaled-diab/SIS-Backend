package com.sis.service;

import com.sis.entities.FacultyMember;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import com.sis.dao.FacultyRepository;

@Service
public class FacultyService extends BaseServiceImp<FacultyMember>{
	
	@Autowired
	private  FacultyRepository facultyRepository;
	@Override
	public JpaRepository<FacultyMember, Long> Repository() {
		return facultyRepository;
	}
}
