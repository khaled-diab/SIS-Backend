package com.sis.service;

import com.sis.dao.CourseRepository;
import com.sis.dao.specification.CourseSpecification;
import com.sis.dto.CourseDTO;
import com.sis.entities.Course;
import com.sis.entities.Student;
import com.sis.entities.mapper.CourseMapper;
import com.sis.util.PageQueryUtil;
import com.sis.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CourseService extends BaseServiceImp<Course>{

	@Autowired
	private CourseRepository courseRepository;

	@Autowired
	private CourseMapper courseMapper;

	@Override
	public JpaRepository<Course, Long> Repository() {
		return courseRepository;
	}

	public void update(CourseDTO dto){
		Optional<Course> object = courseRepository.findById(dto.getId());
		if(object.isPresent()){
			Course member = courseMapper.toEntity(dto);
			courseRepository.save(member);
		}
		else{
			throw new RuntimeException("Course not found");
		}
	}


	public PageResult<CourseDTO> search(PageQueryUtil pageUtil, String key){

		Pageable pageable = PageRequest.of(pageUtil.getPage() - 1, pageUtil.getLimit());

		CourseSpecification courseSpecification=new CourseSpecification(key);

		Page<Course> coursePage = courseRepository.findAll(courseSpecification,pageable);

		PageResult<Course> pageResult = new PageResult<>(coursePage.getContent(), (int) coursePage.getTotalElements(),
				pageUtil.getLimit(), pageUtil.getPage());

		return courseMapper.toDataPage(pageResult);
	}
}
