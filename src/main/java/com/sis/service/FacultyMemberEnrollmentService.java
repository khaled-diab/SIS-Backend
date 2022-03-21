package com.sis.service;

import com.sis.dao.FacultyMemberEnrollmentRepository;
import com.sis.dao.specification.FacultyMemberEnrollmentSpecification;
import com.sis.dto.FacultyMemberEnrollment.FacultyMemberEnrollmentDTO;
import com.sis.dto.FacultyMemberEnrollment.FacultyMemberEnrollmentRequestDTO;
import com.sis.entities.Course;
import com.sis.entities.FacultyMember;
import com.sis.entities.FacultyMemberEnrollment;
import com.sis.entities.StudentEnrollment;
import com.sis.entities.mapper.FacultyMemberEnrollmentMapper;
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
import java.util.Collection;
import java.util.List;

@Service
@AllArgsConstructor
public class FacultyMemberEnrollmentService extends BaseServiceImp<FacultyMemberEnrollment> {

    private final FacultyMemberEnrollmentRepository facultyMemberEnrollmentRepository;
    private final FacultyMemberEnrollmentMapper facultyMemberEnrollmentMapper;

    @Override
    public JpaRepository<FacultyMemberEnrollment, Long> Repository() {
        return facultyMemberEnrollmentRepository;
    }

    public PageResult<FacultyMemberEnrollmentDTO> search(PageQueryUtil pageUtil, FacultyMemberEnrollmentRequestDTO facultyMemberEnrollmentRequestDTO) {
        Page<FacultyMemberEnrollment> facultyMemberEnrollmentPage;
        String searchValue = facultyMemberEnrollmentRequestDTO.getSearchValue();

        Long filterCollege = facultyMemberEnrollmentRequestDTO.getFilterCollege();

        Long filterDepartment = facultyMemberEnrollmentRequestDTO.getFilterDepartment();

        Long filterAcademicYear = facultyMemberEnrollmentRequestDTO.getFilterAcademicYear();

        Long filterAcademicTerm = facultyMemberEnrollmentRequestDTO.getFilterAcademicTerm();

        Long filterFacultyMember = facultyMemberEnrollmentRequestDTO.getFilterFacultyMember();

        Pageable pageable = PageRequest.of(pageUtil.getPage() - 1, pageUtil.getLimit(), constructSortObject(facultyMemberEnrollmentRequestDTO));
        if ((searchValue != null && !searchValue.trim().isEmpty()) || filterCollege != null || filterDepartment != null ||
                filterAcademicYear != null || filterAcademicTerm != null || filterFacultyMember != null) {
            FacultyMemberEnrollmentSpecification studentEnrollmentSpecification = new FacultyMemberEnrollmentSpecification(searchValue, filterCollege, filterDepartment,
                    filterAcademicYear, filterAcademicTerm, filterFacultyMember);

            facultyMemberEnrollmentPage = facultyMemberEnrollmentRepository.findAll(studentEnrollmentSpecification, pageable);
        } else {
            facultyMemberEnrollmentPage = facultyMemberEnrollmentRepository.findAll(pageable);
        }
        PageResult<FacultyMemberEnrollment> pageResult = new PageResult<>(facultyMemberEnrollmentPage.getContent(), (int) facultyMemberEnrollmentPage.getTotalElements(),
                pageUtil.getLimit(), pageUtil.getPage());

        return facultyMemberEnrollmentMapper.toDataPage(pageResult);
    }

    private Sort constructSortObject(FacultyMemberEnrollmentRequestDTO facultyMemberEnrollmentRequestDTO) {
        if (facultyMemberEnrollmentRequestDTO.getSortDirection() == null) {
            return Sort.by(Sort.Direction.ASC, "college");
        }
        return Sort.by(Sort.Direction.valueOf(facultyMemberEnrollmentRequestDTO.getSortDirection()), facultyMemberEnrollmentRequestDTO.getSortBy());
    }

    //UC011
    public ArrayList<FacultyMemberEnrollment> getFacultyMemberCourses(long academicYearId, long
            academicTermId, long facultyMemberId){
        return this.facultyMemberEnrollmentRepository.findFacultyMemberCourses(academicYearId, academicTermId, facultyMemberId);
    }


}
