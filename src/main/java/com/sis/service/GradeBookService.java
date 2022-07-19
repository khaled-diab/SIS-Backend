package com.sis.service;

import com.sis.dto.course.CourseDTO;
import com.sis.dto.gradeBook.GradeBookDTO;
import com.sis.dto.gradeBook.GradeBookRequestDTO;
import com.sis.dto.student.StudentDTO;
import com.sis.dto.studentEnrollment.StudentEnrollmentDTO;
import com.sis.entity.*;
import com.sis.entity.mapper.CourseMapper;
import com.sis.entity.mapper.GradeBookMapper;
import com.sis.entity.mapper.StudentEnrollmentMapper;
import com.sis.repository.GradeBookRepository;
import com.sis.repository.StudentEnrollmentRepository;
import com.sis.repository.TimetableRepository;
import com.sis.repository.specification.GradeBookSpecification;
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
public class GradeBookService extends BaseServiceImp<GradeBook> {

    private final GradeBookRepository gradeBookRepository;
    private final GradeBookMapper gradeBookMapper;

    private final CourseService courseService;

    private final StudentEnrollmentRepository studentEnrollmentRepository;

    private final StudentEnrollmentMapper studentEnrollmentMapper;

    private final TimetableRepository timetableRepository;

    private final AcademicTermService academicTermService;

    private final FacultyMemberService facultyMemberService;

    private CourseMapper courseMapper;

    @Override
    public JpaRepository<GradeBook, Long> Repository() {
        return gradeBookRepository;
    }

    public PageResult<GradeBookDTO> filter(PageQueryUtil pageUtil, GradeBookRequestDTO gradeBookRequestDTO) {
        Page<GradeBook> gradeBookPage;

        Long filterAcademicTerm = gradeBookRequestDTO.getFilterAcademicTerm();

        Long filterCourse = gradeBookRequestDTO.getFilterCourse();

        Long filterStudent = gradeBookRequestDTO.getFilterStudent();

        Long filterFacultyMember = gradeBookRequestDTO.getFilterFacultyMember();

        Pageable pageable = PageRequest.of(pageUtil.getPage() - 1, pageUtil.getLimit(), constructSortObject(gradeBookRequestDTO));
        if (filterAcademicTerm != null || filterCourse != null || filterStudent != null || filterFacultyMember != null) {
            GradeBookSpecification gradeBookSpecification =
                    new GradeBookSpecification(filterAcademicTerm, filterCourse, filterStudent, filterFacultyMember);

            gradeBookPage = gradeBookRepository.findAll(gradeBookSpecification, pageable);
        } else {
            gradeBookPage = gradeBookRepository.findAll(pageable);
        }
        PageResult<GradeBook> pageResult = new PageResult<>(gradeBookPage.getContent(), (int) gradeBookPage.getTotalElements(),
                pageUtil.getLimit(), pageUtil.getPage());

        return gradeBookMapper.toDataPage(pageResult);
    }

    private Sort constructSortObject(GradeBookRequestDTO gradeBookRequestDTO) {
        if (gradeBookRequestDTO.getSortDirection() == null) {
            return Sort.by(Sort.Direction.ASC, "student");
        }
        return Sort.by(Sort.Direction.valueOf(gradeBookRequestDTO.getSortDirection()), gradeBookRequestDTO.getSortBy());
    }

    // by ziad
    public List<StudentDTO> getStudentsByCourseId(Long courseId) {
        Course course = this.courseService.findById(courseId);
        List<StudentEnrollmentDTO> studentEnrollmentDTOS = this.studentEnrollmentMapper
                .toDTOs(this.studentEnrollmentRepository.getStudentEnrollmentsByCourseId(course.getId()));
        ArrayList<StudentDTO> studentDTOS = new ArrayList<>();
        for (StudentEnrollmentDTO studentEnrollmentDTO : studentEnrollmentDTOS) {
            studentDTOS.add(studentEnrollmentDTO.getStudentDTO());
        }
        return studentDTOS;
    }

    // by ziad
    public ArrayList<CourseDTO> getCoursesByFacultyMemberId(Long termId, Long facultyMemberId) {
        AcademicTerm academicTerm = this.academicTermService.findById(termId);
        FacultyMember facultyMember = this.facultyMemberService.findById(facultyMemberId);
        ArrayList<Long> courseIds = this.timetableRepository
                .getTimetablesByAcademicTerm_IdAndFacultyMemberId
                        (academicTerm.getId(), facultyMember.getId());
        ArrayList<Course> courses = new ArrayList<>();
        ArrayList<CourseDTO> courseDTOs = new ArrayList<>();
        if (courseIds != null && courseIds.size() > 0) {
            for (long id : courseIds) {
                Course course = this.courseService.findById(id);
                courses.add(course);
            }
            courseDTOs = this.courseMapper.toDTOs(courses);
            return courseDTOs;
        }
        return null;
    }


}
