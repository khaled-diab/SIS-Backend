package com.sis.service;

import com.sis.dao.ClassroomDao;
import com.sis.entities.Classroom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class ClassroomService extends BaseServiceImp<Classroom>{
	
	@Autowired
	private  ClassroomDao ClassroomDao;
	@Override
	public JpaRepository<Classroom, Long> Repository() {
		return ClassroomDao;
	}
}
