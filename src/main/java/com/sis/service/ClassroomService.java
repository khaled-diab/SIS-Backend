package com.sis.service;

import com.sis.entity.Classroom;
import com.sis.repository.ClassroomSpecification;
import com.sis.repository.ClassroomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassroomService extends BaseServiceImp<Classroom>{

	@Autowired
	private ClassroomRepository classroomrepository;

	@Override
	public JpaRepository<Classroom, Long> Repository() {
		return classroomrepository;
	}

	public List<Classroom> search(String key){
		ClassroomSpecification classroomSpecification = new ClassroomSpecification(key);
		return classroomrepository.findAll(classroomSpecification);
	}
}
