package com.sis.service;

import com.sis.dao.StudentEnrollmentRepository;
import com.sis.dao.specification.StudentEnrollmentSpecification;
import com.sis.dto.studentEnrollment.StudentEnrollmentDTO;
import com.sis.dto.studentEnrollment.StudentEnrollmentRequestDTO;
import com.sis.entities.StudentEnrollment;
import com.sis.entities.mapper.StudentEnrollmentMapper;
import com.sis.util.PageQueryUtil;
import com.sis.util.PageResult;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class StudentEnrollmentService extends BaseServiceImp<StudentEnrollment> {

    private final StudentEnrollmentRepository studentEnrollmentRepository;
    private final StudentEnrollmentMapper studentEnrollmentMapper;

    @Override
    public JpaRepository<StudentEnrollment, Long> Repository() {
        return null;
    }

    public PageResult<StudentEnrollmentDTO> search(PageQueryUtil pageUtil, StudentEnrollmentRequestDTO studentEnrollmentRequestDTO) {
        Page<StudentEnrollment> studentEnrollmentPage;
        String searchValue = studentEnrollmentRequestDTO.getSearchValue();

        Long filterCollege = studentEnrollmentRequestDTO.getFilterCollege();

        Long filterDepartment = studentEnrollmentRequestDTO.getFilterDepartment();

        Long filterAcademicYear = studentEnrollmentRequestDTO.getFilterAcademicYear();

        Long filterAcademicTerm = studentEnrollmentRequestDTO.getFilterAcademicTerm();

        Long filterCourse = studentEnrollmentRequestDTO.getFilterCourse();

        Long filterStudyType = studentEnrollmentRequestDTO.getFilterStudyType();

        Long filterSectionNumber = studentEnrollmentRequestDTO.getFilterSectionNumber();

        Long filterMajor = studentEnrollmentRequestDTO.getFilterMajor();

        Pageable pageable = PageRequest.of(pageUtil.getPage() - 1, pageUtil.getLimit(), constructSortObject(studentEnrollmentRequestDTO));
        if ((searchValue != null && !searchValue.trim().isEmpty()) || filterCollege != null || filterDepartment != null ||
                filterAcademicYear != null || filterAcademicTerm != null || filterCourse != null ||
                filterStudyType != null || filterSectionNumber != null || filterMajor != null) {
            StudentEnrollmentSpecification studentEnrollmentSpecification = new StudentEnrollmentSpecification(searchValue, filterCollege, filterDepartment,
                    filterAcademicYear, filterAcademicTerm, filterCourse, filterStudyType, filterSectionNumber, filterMajor);

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
            return Sort.by(Sort.Direction.ASC, "nameAr");
        }
        return Sort.by(Sort.Direction.valueOf(studentEnrollmentRequestDTO.getSortDirection()), studentEnrollmentRequestDTO.getSortBy());
    }
}
