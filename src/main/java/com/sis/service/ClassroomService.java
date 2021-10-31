package com.sis.service;

import com.sis.dao.ClassroomDao;
import com.sis.dao.ClassroomSpecification;
import com.sis.entities.Classroom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassroomService extends BaseServiceImp<Classroom>{
	
	@Autowired
	private  ClassroomDao classroomDao;

	@Override
	public JpaRepository<Classroom, Long> Repository() {
		return classroomDao;
	}

	public List<Classroom> search(String key){
		ClassroomSpecification classroomSpecification = new ClassroomSpecification(key);
		return classroomDao.findAll(classroomSpecification);
	}
}
