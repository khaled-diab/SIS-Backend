package com.sis.service;

import com.sis.dto.college.CollegeDTO;
import com.sis.dto.college.CollegeRequestDTO;
import com.sis.entity.BaseEntity;
import com.sis.entity.College;
import com.sis.entity.mapper.CollegeMapper;
import com.sis.repository.CollegeRepository;
import com.sis.util.Constants;
import com.sis.util.PageResult;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CollegeService extends BaseServiceImp<College> {

    private final CollegeRepository collegeRepository;
    private final CollegeMapper collegeMapper;

    private final RedisTemplate<String, Map<String, Long>> redisTemplate;

    @PostConstruct
    public void init() {
        this.cashAllColleges();
    }


    @Override
    public JpaRepository<College, Long> Repository() {
        return collegeRepository;
    }

    public PageResult<CollegeDTO> getCollegesPage(Integer page, Integer size, CollegeRequestDTO collegeRequestDTO) {
        Page<College> collegePage;
        PageRequest pageRequest = PageRequest.of(page, size, constructSortObject(collegeRequestDTO));
        if (collegeRequestDTO.getFilterValue() != null && !collegeRequestDTO.getFilterValue().equals("")) {
            collegePage = this.collegeRepository
                    .findAllByCodeContainingOrNameArContainingOrNameEnContaining(
                            collegeRequestDTO.getFilterValue(),
                            collegeRequestDTO.getFilterValue(),
                            collegeRequestDTO.getFilterValue(),
                            pageRequest);
        } else {
            collegePage = this.collegeRepository.findAll(pageRequest);
        }
        return collegeMapper.toDataPage(new PageResult<>(collegePage.getContent(),
                (int) collegePage.getTotalElements(),
                collegePage.getSize(),
                collegePage.getNumber()));
    }

    private Sort constructSortObject(CollegeRequestDTO collegeRequestDTO) {
        if (collegeRequestDTO.getSortDirection() == null) {
            return Sort.by(Sort.Direction.ASC, "nameAr");
        }
        return Sort.by(Sort.Direction.valueOf(collegeRequestDTO.getSortDirection()), collegeRequestDTO.getSortBy());
    }

    private void cashAllColleges() {
        Map<String, Long> collect = collegeRepository.findAll().parallelStream().collect(Collectors.toMap(College::getCode, BaseEntity::getId));
        redisTemplate.opsForSet().add(Constants.COLLEGES_CASH_KEY, collect);
    }
}


