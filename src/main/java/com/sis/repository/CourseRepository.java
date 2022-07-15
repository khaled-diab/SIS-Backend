package com.sis.repository;

import com.sis.entity.Course;

import java.util.List;


public interface CourseRepository extends BaseRepository<Course> {
    List<Course> getCoursesByDepartmentId(Long departmentId);
}



