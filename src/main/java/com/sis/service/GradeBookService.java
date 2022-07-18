package com.sis.service;

import com.sis.dto.gradeBook.GradeBookDTO;
import com.sis.dto.gradeBook.GradeBookRequestDTO;
import com.sis.dto.studentEnrollment.StudentEnrollmentDTO;
import com.sis.dto.studentEnrollment.StudentEnrollmentRequestDTO;
import com.sis.entity.*;
import com.sis.entity.mapper.GradeBookMapper;
import com.sis.repository.GradeBookRepository;
import com.sis.repository.specification.GradeBookSpecification;
import com.sis.repository.specification.StudentEnrollmentSpecification;
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
public class GradeBookService extends BaseServiceImp<GradeBook> {

    private final GradeBookRepository gradeBookRepository;
    private final GradeBookMapper gradeBookMapper;

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

}
