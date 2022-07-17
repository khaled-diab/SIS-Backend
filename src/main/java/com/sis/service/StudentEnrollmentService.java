package com.sis.service;


import com.sis.dto.course.CourseDTO;
import com.sis.dto.student.StudentDTO;
import com.sis.dto.studentEnrollment.StudentArray;
import com.sis.dto.studentEnrollment.StudentEnrollmentDTO;
import com.sis.dto.studentEnrollment.StudentEnrollmentRequestDTO;
import com.sis.entity.AcademicTerm;
import com.sis.entity.AcademicYear;
import com.sis.entity.Section;
import com.sis.entity.StudentEnrollment;
import com.sis.entity.mapper.CourseMapper;
import com.sis.entity.mapper.StudentEnrollmentMapper;
import com.sis.exception.StudentEnrollmentFieldNotUniqueException;
import com.sis.repository.StudentEnrollmentRepository;
import com.sis.repository.specification.StudentEnrollmentSpecification;
import com.sis.util.MessageResponse;
import com.sis.util.PageQueryUtil;
import com.sis.util.PageResult;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.Collection;

@Service
@AllArgsConstructor
public class StudentEnrollmentService extends BaseServiceImp<StudentEnrollment> {

    private final StudentEnrollmentRepository studentEnrollmentRepository;
    private final StudentEnrollmentMapper studentEnrollmentMapper;

    private CourseMapper courseMapper;

    @Override
    public JpaRepository<StudentEnrollment, Long> Repository() {
        return studentEnrollmentRepository;
    }

    public PageResult<StudentEnrollmentDTO> search(PageQueryUtil pageUtil, StudentEnrollmentRequestDTO studentEnrollmentRequestDTO) {
        Page<StudentEnrollment> studentEnrollmentPage;
        String searchValue = studentEnrollmentRequestDTO.getSearchValue();

        Long filterCollege = studentEnrollmentRequestDTO.getFilterCollege();

        Long filterDepartment = studentEnrollmentRequestDTO.getFilterDepartment();

        Long filterAcademicYear = studentEnrollmentRequestDTO.getFilterAcademicYear();

        Long filterAcademicTerm = studentEnrollmentRequestDTO.getFilterAcademicTerm();

        Long filterCourse = studentEnrollmentRequestDTO.getFilterCourse();

        Long filterStudent = studentEnrollmentRequestDTO.getFilterStudent();

        Long filterStudyType = studentEnrollmentRequestDTO.getFilterStudyType();

        Long filterSection = studentEnrollmentRequestDTO.getFilterSection();

        Long filterMajor = studentEnrollmentRequestDTO.getFilterMajor();

        Pageable pageable = PageRequest.of(pageUtil.getPage() - 1, pageUtil.getLimit(), constructSortObject(studentEnrollmentRequestDTO));
        if ((searchValue != null && !searchValue.trim().isEmpty()) || filterCollege != null || filterDepartment != null || filterAcademicYear != null || filterAcademicTerm != null ||
                filterCourse != null || filterStudent != null || filterStudyType != null || filterSection != null || filterMajor != null) {
            StudentEnrollmentSpecification studentEnrollmentSpecification = new StudentEnrollmentSpecification(searchValue,
                    filterCollege, filterDepartment, filterAcademicYear, filterAcademicTerm, filterCourse,
                    filterStudent, filterStudyType, filterSection, filterMajor);

            studentEnrollmentPage = studentEnrollmentRepository.findAll(studentEnrollmentSpecification, pageable);
        } else {
            studentEnrollmentPage = studentEnrollmentRepository.findAll(pageable);
        }
        PageResult<StudentEnrollment> pageResult = new PageResult<>(studentEnrollmentPage.getContent(), (int) studentEnrollmentPage.getTotalElements(),
                pageUtil.getLimit(), pageUtil.getPage());

        return studentEnrollmentMapper.toDataPage(pageResult);
    }

    private Sort constructSortObject(StudentEnrollmentRequestDTO studentEnrollmentRequestDTO) {
        if (studentEnrollmentRequestDTO.getSortDirection() == null) {
            return Sort.by(Sort.Direction.ASC, "college");
        }
        return Sort.by(Sort.Direction.valueOf(studentEnrollmentRequestDTO.getSortDirection()), studentEnrollmentRequestDTO.getSortBy());
    }

    public int countBySection(long sectionId) {
        return this.studentEnrollmentRepository.countAllBySectionId(sectionId);
    }

    //Abdo.Amr
    public Collection<Section> findStudentSections(AcademicYear academicYear, AcademicTerm academicTerm, long studentId) {
        Collection<StudentEnrollment> studentEnrollments = this.studentEnrollmentRepository.findStudentEnrollmentByAcademicYearAndAcademicTermAndStudentId(academicYear, academicTerm, studentId);
        ArrayList<Section> sections = new ArrayList<>();
        for (StudentEnrollment studentEnrollment : studentEnrollments) {
            if (studentEnrollment.getSection() != null) {
                sections.add(studentEnrollment.getSection());
            }
        }
        return sections;
    }

    //Abdo.Amr
    public Section findStudentSection(long academicYearId, long academicTermId, long studentId, long courseId) {
        StudentEnrollment studentEnrollment = this.studentEnrollmentRepository.findStudentSection(academicYearId, academicTermId, studentId, courseId);

        if (studentEnrollment != null) {
            if (studentEnrollment.getSection() != null) {
                return studentEnrollment.getSection();
            }
        }
        return null;
    }

    //Abdo.Amr
    public ArrayList<CourseDTO> getStudentCourses(long academicYearId, long academicTermId, long studentId) {
        ArrayList<StudentEnrollment> studentEnrollments = this.studentEnrollmentRepository.findStudentCourses(academicYearId, academicTermId, studentId);
        ArrayList<CourseDTO> courseDTOs = new ArrayList<>();
        for (StudentEnrollment studentEnrollment : studentEnrollments) {
            courseDTOs.add(this.courseMapper.toDTO(studentEnrollment.getCourse()));
        }
        return courseDTOs;
    }

    public ArrayList<StudentEnrollment> save(@RequestBody StudentArray dto) {
        ArrayList<StudentEnrollment> studentEnrollments = new ArrayList<>();
        for (StudentDTO studentDTO : dto.getStudentDTOS()) {
            StudentEnrollmentDTO studentEnrollmentDTO = new StudentEnrollmentDTO();
            studentEnrollmentDTO.setAcademicYearDTO(dto.getStudentEnrollmentDTO().getAcademicYearDTO());
            studentEnrollmentDTO.setAcademicTermDTO(dto.getStudentEnrollmentDTO().getAcademicTermDTO());
            studentEnrollmentDTO.setCollegeDTO(dto.getStudentEnrollmentDTO().getCollegeDTO());
            studentEnrollmentDTO.setDepartmentDTO(dto.getStudentEnrollmentDTO().getDepartmentDTO());
            studentEnrollmentDTO.setMajorDTO(dto.getStudentEnrollmentDTO().getMajorDTO());
            studentEnrollmentDTO.setStudyTypeDTO(dto.getStudentEnrollmentDTO().getStudyTypeDTO());
            studentEnrollmentDTO.setCourseDTO(dto.getStudentEnrollmentDTO().getCourseDTO());
            studentEnrollmentDTO.setSectionDTO(dto.getStudentEnrollmentDTO().getSectionDTO());
            studentEnrollmentDTO.setStudentDTO(studentDTO);
            StudentEnrollment studentEnrollment = this.studentEnrollmentRepository.findStudentEnrollmentByCourseIdAndSectionIdAndStudentId
                    (dto.getStudentEnrollmentDTO().getCourseDTO().getId(), dto.getStudentEnrollmentDTO().getSectionDTO().getId(),
                            studentDTO.getId());
            if (studentEnrollment == null) {
                studentEnrollments.add(this.studentEnrollmentMapper.toEntity(studentEnrollmentDTO));
            } else {
                throw new StudentEnrollmentFieldNotUniqueException("Student with id: " + studentDTO.getUniversityId()
                        + " Already Enrolled in this section", "exists");
            }
        }
        return studentEnrollments;
    }
}
