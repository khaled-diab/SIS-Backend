package com.sis.service;

import com.sis.dto.major.MajorDTO;
import com.sis.dto.major.MajorRequestDTO;
import com.sis.dto.major.MajorTableRecordsDTO;
import com.sis.entity.Department;
import com.sis.entity.Major;
import com.sis.entity.mapper.MajorMapper;
import com.sis.entity.mapper.MajorTableRecordsMapper;
import com.sis.repository.MajorRepository;
import com.sis.repository.specification.MajorSpecification;
import com.sis.util.PageQueryUtil;
import com.sis.util.PageResult;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MajorService extends BaseServiceImp<Major> {

    private MajorRepository majorRepository;

    private MajorMapper majorMapper;

    private MajorTableRecordsMapper majorTableRecordsMapper;

    private final DepartmentService departmentService;

    @Override
    public JpaRepository<Major, Long> Repository() {
        return majorRepository;
    }

    public List<MajorDTO> getMajorsByDepartmentId(Long departmentId) {
        Department department = this.departmentService.findById(departmentId);
        return this.majorMapper.toDTOs(this.majorRepository.getMajorsByDepartment_Id(department.getId()));
    }

    public PageResult<MajorTableRecordsDTO> filter(PageQueryUtil pageUtil, MajorRequestDTO majorRequestDTO) {
        Page<Major> majorPage;

        String searchValue = majorRequestDTO.getSearchValue();
        Long filterCollege = majorRequestDTO.getFilterCollege();
        Long filterDepartment = majorRequestDTO.getFilterDepartment();

        Pageable pageable = PageRequest.of(pageUtil.getPage() - 1, pageUtil.getLimit(), constructSortObject(majorRequestDTO));
        if ((searchValue != null && !searchValue.trim().isEmpty()) || filterCollege != null || filterDepartment != null) {
            MajorSpecification majorSpecification = new MajorSpecification(searchValue, filterCollege, filterDepartment);

            majorPage = majorRepository.findAll(majorSpecification, pageable);
        } else {
            majorPage = majorRepository.findAll(pageable);
        }
        PageResult<Major> pageResult = new PageResult<>(majorPage.getContent(), (int) majorPage.getTotalElements(),
                pageUtil.getLimit(), pageUtil.getPage());

        return majorTableRecordsMapper.toDataPage(pageResult);
    }

    private Sort constructSortObject(MajorRequestDTO majorRequestDTO) {
        if (majorRequestDTO.getSortDirection() == null) {
            return Sort.by(Sort.Direction.ASC, "nameAr");
        }
        return Sort.by(Sort.Direction.valueOf(majorRequestDTO.getSortDirection()), majorRequestDTO.getSortBy());
    }

}
