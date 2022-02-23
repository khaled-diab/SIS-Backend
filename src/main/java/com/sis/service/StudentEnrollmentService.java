package com.sis.service;

import com.sis.dao.StudentEnrollmentRepository;
import com.sis.dao.specification.StudentEnrollmentSpecification;
import com.sis.dto.studentEnrollment.StudentEnrollmentDTO;
import com.sis.dto.studentEnrollment.StudentEnrollmentRequestDTO;
import com.sis.entities.Section;
import com.sis.entities.StudentEnrollment;
import com.sis.entities.mapper.SectionMapper;
import com.sis.entities.mapper.StudentEnrollmentMapper;
import com.sis.util.PageQueryUtil;
import com.sis.util.PageResult;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
@AllArgsConstructor
public class StudentEnrollmentService extends BaseServiceImp<StudentEnrollment> {

    private final StudentEnrollmentRepository studentEnrollmentRepository;
    private final StudentEnrollmentMapper studentEnrollmentMapper;

    @Autowired
    private SectionMapper sectionMapper;


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

    public Collection<Section> findSections(long academicYearId, long academicTermId, long studentId) {
        Collection<Long> studentEnrollments = this.studentEnrollmentRepository.findStudentEnrollmentsByStudent(studentId);
        Collection<Section> sectionDTOs = new ArrayList<>();
        for (long id : studentEnrollments) {
            StudentEnrollment studentEnrollment = this.findById(id);
            if (studentEnrollment.getAcademicTerm().getId() == academicTermId && studentEnrollment.getAcademicYear().getId() == academicYearId) {
                sectionDTOs.add(studentEnrollment.getSection());
            }
        }
        return sectionDTOs;
    }


}
