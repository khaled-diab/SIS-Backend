package com.sis.service;

import com.sis.dao.CollegeRepository;
import com.sis.dto.college.CollegeDTO;
import com.sis.dto.college.CollegeFilterDTO;
import com.sis.entities.College;
import com.sis.entities.mapper.CollegeMapper;
import com.sis.util.PageResult;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CollegeService extends BaseServiceImp<College> {

    private final CollegeRepository collegeRepository;
    private final CollegeMapper collegeMapper;


    @Override
    public JpaRepository<College, Long> Repository() {
        return collegeRepository;
    }

    public PageResult<CollegeDTO> getCollegesPage(Integer page, Integer size, CollegeFilterDTO collegeFilterDTO) {
        Sort sort = Sort.by(Sort.Direction.valueOf(collegeFilterDTO.getSortDirection()), collegeFilterDTO.getSortBy());
        PageRequest pageRequest = PageRequest.of(page, size, sort);
        Page<College> collegePage;
        if (collegeFilterDTO.getFilterValue() != null && !collegeFilterDTO.getFilterValue().equals("")) {
            collegePage = this.collegeRepository
                    .findAllByCodeContainingOrNameArContainingOrNameEnContaining(
                            collegeFilterDTO.getFilterValue(),
                            collegeFilterDTO.getFilterValue(),
                            collegeFilterDTO.getFilterValue(),
                            pageRequest);
        } else {
            collegePage = this.collegeRepository.findAll(pageRequest);
        }
        return collegeMapper.toDataPage(new PageResult<>(collegePage.getContent(),
                (int) collegePage.getTotalElements(),
                collegePage.getSize(),
                collegePage.getNumber()));
    }
}
