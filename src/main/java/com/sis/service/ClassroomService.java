package com.sis.service;

import com.sis.entity.Classroom;
import com.sis.repository.ClassroomDao;
import com.sis.repository.ClassroomSpecification;
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
