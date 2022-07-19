package com.sis.service;


import com.sis.dto.course.CourseDTO;
import com.sis.dto.course.CourseRequestDTO;
import com.sis.dto.course.CourseTableRecordsDTO;
import com.sis.entity.Course;
import com.sis.entity.Department;
import com.sis.entity.mapper.CourseMapper;
import com.sis.entity.mapper.CourseTableRecordsMapper;
import com.sis.repository.CourseRepository;
import com.sis.repository.specification.CourseSpecification;
import com.sis.util.PageQueryUtil;
import com.sis.util.PageResult;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CourseService extends BaseServiceImp<Course> {

    private CourseRepository courseRepository;
    private CourseMapper courseMapper;
    private CourseTableRecordsMapper courseTableRecordsMapper;
    StudentEnrollmentService studentEnrollmentService;

    private final DepartmentService departmentService;

    private TimetableService timetableService;

    @Override
    public JpaRepository<Course, Long> Repository() {
        return courseRepository;
    }

    public PageResult<CourseDTO> search(PageQueryUtil pageUtil, CourseRequestDTO courseRequestDTO) {
        Page<Course> coursePage;
        String searchValue = courseRequestDTO.getSearchValue();

        Long filterCollege = courseRequestDTO.getFilterCollege();

        Long filterDepartment = courseRequestDTO.getFilterDepartment();

        Pageable pageable = PageRequest.of(pageUtil.getPage() - 1, pageUtil.getLimit(), constructSortObject(courseRequestDTO));
        if ((searchValue != null && !searchValue.trim().isEmpty()) || filterCollege != null || filterDepartment != null) {
            CourseSpecification courseSpecification = new CourseSpecification(searchValue, filterCollege, filterDepartment);

            coursePage = courseRepository.findAll(courseSpecification, pageable);
        } else {
            coursePage = courseRepository.findAll(pageable);
        }
        PageResult<Course> pageResult = new PageResult<>(coursePage.getContent(), (int) coursePage.getTotalElements(),
                pageUtil.getLimit(), pageUtil.getPage());

        return courseMapper.toDataPage(pageResult);
    }

    private Sort constructSortObject(CourseRequestDTO courseRequestDTO) {
        if (courseRequestDTO.getSortDirection() == null) {
            return Sort.by(Sort.Direction.ASC, "nameAr");
        }
        return Sort.by(Sort.Direction.valueOf(courseRequestDTO.getSortDirection()), courseRequestDTO.getSortBy());
    }

    //Abdo.Amr
    public ArrayList<CourseDTO> getStudentCourses(long academicYearId, long academicTermId, long studentId) {
        return this.studentEnrollmentService.getStudentCourses(academicYearId, academicTermId, studentId);
    }

    //Abdo.Amr
    public ArrayList<CourseDTO> findFacultyMemberCourses(long academicYearId, long academicTermId, long facultyMemberId) {
        ArrayList<Long> courseIds = this.timetableService.findFacultyMemberCourses(academicYearId, academicTermId, facultyMemberId);
        ArrayList<Course> courses = new ArrayList<>();
        ArrayList<CourseDTO> courseDTOs = new ArrayList<>();

        if (courseIds != null && courseIds.size() > 0) {
            for (long id : courseIds) {
                Course course = this.findById(id);
                courses.add(course);
            }
            courseDTOs = this.courseMapper.toDTOs(courses);
            return courseDTOs;
        }
        return null;
    }

    public PageResult<CourseTableRecordsDTO> filter(PageQueryUtil pageUtil, CourseRequestDTO courseRequestDTO) {
        Page<Course> coursePage;
        String searchValue = courseRequestDTO.getSearchValue();

        Long filterCollege = courseRequestDTO.getFilterCollege();

        Long filterDepartment = courseRequestDTO.getFilterDepartment();

        Pageable pageable = PageRequest.of(pageUtil.getPage() - 1, pageUtil.getLimit(), constructSortObject(courseRequestDTO));
        if ((searchValue != null && !searchValue.trim().isEmpty()) || filterCollege != null || filterDepartment != null) {
            CourseSpecification courseSpecification = new CourseSpecification(searchValue, filterCollege, filterDepartment);

            coursePage = courseRepository.findAll(courseSpecification, pageable);
        } else {
            coursePage = courseRepository.findAll(pageable);
        }
        PageResult<Course> pageResult = new PageResult<>(coursePage.getContent(), (int) coursePage.getTotalElements(),
                pageUtil.getLimit(), pageUtil.getPage());

        return courseTableRecordsMapper.toDataPage(pageResult);
    }

    public List<CourseDTO> getCoursesByDepartmentId(Long departmentId) {
        Department department = this.departmentService.findById(departmentId);
        return this.courseMapper.toDTOs(this.courseRepository.getCoursesByDepartmentId(department.getId()));
    }

}
