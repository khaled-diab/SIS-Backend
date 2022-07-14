package com.sis.service;

import com.sis.dto.facultyMember.FacultyMemberDTO;
import com.sis.dto.facultyMember.FacultyMemberRequestDTO;
import com.sis.dto.facultyMember.FacultyMemberTableRecordsDTO;
import com.sis.entity.FacultyMember;
import com.sis.entity.mapper.FacultyMemberMapper;
import com.sis.entity.mapper.FacultyMemberTableRecordsMapper;
import com.sis.repository.FacultyMemberRepository;
import com.sis.repository.specification.FacultyMemberSpecification;
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
public class FacultyMemberService extends BaseServiceImp<FacultyMember> {


    private final FacultyMemberRepository facultyMemberRepository;
    private final FacultyMemberMapper facultyMemberMapper;
    private final FacultyMemberTableRecordsMapper facultyMemberTableRecordsMapper;

    @Override
    public JpaRepository<FacultyMember, Long> Repository() {
        return facultyMemberRepository;
    }

    public PageResult<FacultyMemberDTO> search(PageQueryUtil pageUtil, FacultyMemberRequestDTO facultyMemberRequestDTO) {
        Page<FacultyMember> facultyMemberPage;

        String searchValue = facultyMemberRequestDTO.getSearchValue();
        Long filterCollege = facultyMemberRequestDTO.getFilterCollege();
        Long filterDepartment = facultyMemberRequestDTO.getFilterDepartment();

        Pageable pageable = PageRequest.of(pageUtil.getPage() - 1, pageUtil.getLimit(), constructSortObject(facultyMemberRequestDTO));
        if ((searchValue != null && !searchValue.trim().isEmpty()) || filterCollege != null || filterDepartment != null) {
            FacultyMemberSpecification facultyMemberSpecification = new FacultyMemberSpecification(searchValue, filterCollege, filterDepartment);

            facultyMemberPage = facultyMemberRepository.findAll(facultyMemberSpecification, pageable);
        } else {
            facultyMemberPage = facultyMemberRepository.findAll(pageable);
        }
        PageResult<FacultyMember> pageResult = new PageResult<>(facultyMemberPage.getContent(), (int) facultyMemberPage.getTotalElements(),
                pageUtil.getLimit(), pageUtil.getPage());

        return facultyMemberMapper.toDataPage(pageResult);
    }

    private Sort constructSortObject(FacultyMemberRequestDTO facultyMemberRequestDTO) {
        if (facultyMemberRequestDTO.getSortDirection() == null) {
            return Sort.by(Sort.Direction.ASC, "nameAr");
        }
        return Sort.by(Sort.Direction.valueOf(facultyMemberRequestDTO.getSortDirection()), facultyMemberRequestDTO.getSortBy());
    }

    public FacultyMember findByNationalID(String id) {
        return this.facultyMemberRepository.findByNationalID(id);
    }

    public FacultyMember findByUniversityMail(String mail) {
        return this.facultyMemberRepository.findByUniversityMail(mail);
    }

    public FacultyMember findByPhone(String phone) {
        return this.facultyMemberRepository.findByPhone(phone);
    }

    public PageResult<FacultyMemberTableRecordsDTO> filter(PageQueryUtil pageUtil, FacultyMemberRequestDTO facultyMemberRequestDTO) {
        Page<FacultyMember> facultyMemberPage;

        String searchValue = facultyMemberRequestDTO.getSearchValue();
        Long filterCollege = facultyMemberRequestDTO.getFilterCollege();
        Long filterDepartment = facultyMemberRequestDTO.getFilterDepartment();

        Pageable pageable = PageRequest.of(pageUtil.getPage() - 1, pageUtil.getLimit(), constructSortObject(facultyMemberRequestDTO));
        if ((searchValue != null && !searchValue.trim().isEmpty()) || filterCollege != null || filterDepartment != null) {
            FacultyMemberSpecification facultyMemberSpecification = new FacultyMemberSpecification(searchValue, filterCollege, filterDepartment);

            facultyMemberPage = facultyMemberRepository.findAll(facultyMemberSpecification, pageable);
        } else {
            if (facultyMemberRequestDTO.getSortBy().equalsIgnoreCase("nameAr")) {
                facultyMemberRequestDTO.setSortBy("name_ar");
            } else if (facultyMemberRequestDTO.getSortBy().equalsIgnoreCase("collegeId")) {
                facultyMemberRequestDTO.setSortBy("college_id");
            } else if (facultyMemberRequestDTO.getSortBy().equalsIgnoreCase("departmentId")) {
                facultyMemberRequestDTO.setSortBy("department_id");
            }

            Sort sort2 = constructSortObject2(facultyMemberRequestDTO);
            Pageable pageable2 = PageRequest.of(pageUtil.getPage() - 1, pageUtil.getLimit(), sort2);
            facultyMemberPage = facultyMemberRepository.findAllWithUser(pageable2);
        }
        PageResult<FacultyMember> pageResult = new PageResult<>(facultyMemberPage.getContent(), (int) facultyMemberPage.getTotalElements(),
                pageUtil.getLimit(), pageUtil.getPage());

        return facultyMemberTableRecordsMapper.toDataPage(pageResult);
    }

    private Sort constructSortObject2(FacultyMemberRequestDTO facultyMemberRequestDTO) {
        if (facultyMemberRequestDTO.getSortDirection() == null) {
            return Sort.by(Sort.Direction.ASC, "name_ar");
        }
        return Sort.by(Sort.Direction.valueOf(facultyMemberRequestDTO.getSortDirection()), facultyMemberRequestDTO.getSortBy());
    }
}
