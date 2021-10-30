package com.sis.service;

import com.sis.dao.CourseRepository;
import com.sis.dao.CourseSpecification;
import com.sis.dto.CourseDTO;
import com.sis.entities.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CourseService extends BaseServiceImp<Course>{

	@Autowired
	private CourseRepository courseRepository;

	@Override
	public JpaRepository<Course, Long> Repository() {
		return courseRepository;
	}


	public List<Course> search(String key){
		CourseSpecification courseSpecification=new CourseSpecification(key);
		return courseRepository.findAll(courseSpecification);
	}
}
