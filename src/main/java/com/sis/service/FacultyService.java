package com.sis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import com.sis.dao.FacultyRepository;
import com.sis.entities.Faculty;

@Service
public class FacultyService extends BaseServiceImp<Faculty>{
	
	@Autowired
	private  FacultyRepository facultyRepository;
	@Override
	public JpaRepository<Faculty, Long> Repository() {
		return facultyRepository;
	}
}
