package com.sis.service;

import com.sis.dao.FacultyMemberRepository;
import com.sis.dto.facultyMember.FacultyMemberDTO;
import com.sis.dto.facultyMember.FacultyMemberRequestDTO;
import com.sis.entities.FacultyMember;
import com.sis.entities.mapper.FacultyMemberMapper;
import com.sis.specification.FacultyMemberSpecification;
import com.sis.util.PageQueryUtil;
import com.sis.util.PageResult;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class FacultyMemberService extends BaseServiceImp<FacultyMember> {


    private final FacultyMemberRepository facultyMemberRepository;
    private final FacultyMemberMapper facultyMemberMapper;

    @Override
    public JpaRepository<FacultyMember, Long> Repository() {
        return facultyMemberRepository;
    }

    public void update(FacultyMemberDTO dto) {
        Optional<FacultyMember> object = facultyMemberRepository.findById(dto.getId());
        if (object.isPresent()) {
            FacultyMember member = facultyMemberMapper.toEntity(dto);
            facultyMemberRepository.save(member);
        } else {
            throw new RuntimeException("faculty member not found");
        }
    }

    public PageResult<FacultyMemberDTO> search(PageQueryUtil pageUtil, FacultyMemberRequestDTO facultyMemberRequestDTO) {
        Page<FacultyMember> facultyMemberPage;
        String key = facultyMemberRequestDTO.getFilterValue();

        Pageable pageable = PageRequest.of(pageUtil.getPage() - 1, pageUtil.getLimit(), constructSortObject(facultyMemberRequestDTO));
        if (key != null && !key.trim().isEmpty()) {
            FacultyMemberSpecification facultyMemberSpecification = new FacultyMemberSpecification(key);

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

}
