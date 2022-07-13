package com.sis.service;

import com.sis.dto.college.CollegeDTO;
import com.sis.dto.college.GeneralSearchRequest;
import com.sis.entity.College;
import com.sis.entity.mapper.CollegeMapper;
import com.sis.repository.CollegeRepository;
import com.sis.util.MessageResponse;
import com.sis.util.PageResult;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

    public PageResult<CollegeDTO> getCollegesPage(Integer page, Integer size, GeneralSearchRequest generalSearchRequest) {
        Page<College> collegePage;
        PageRequest pageRequest = PageRequest.of(page, size, constructSortObject(generalSearchRequest, "nameAr"));
        if (generalSearchRequest.getFilterValue() != null && !generalSearchRequest.getFilterValue().equals("")) {
            collegePage = this.collegeRepository
                    .findAllByCodeContainingOrNameArContainingOrNameEnContaining(
                            generalSearchRequest.getFilterValue(),
                            generalSearchRequest.getFilterValue(),
                            generalSearchRequest.getFilterValue(),
                            pageRequest);
        } else {
            collegePage = this.collegeRepository.findAll(pageRequest);
        }
        return collegeMapper.toDataPage(new PageResult<>(collegePage.getContent(),
                (int) collegePage.getTotalElements(),
                collegePage.getSize(),
                collegePage.getNumber()));
    }

    public MessageResponse checkCollegeCode(String code) {
        if (Boolean.TRUE.equals(collegeRepository.existsByCode(code))) {
            return new MessageResponse(500, "Code Already Exist", null);
        } else {
            return new MessageResponse(200);
        }
    }
}


