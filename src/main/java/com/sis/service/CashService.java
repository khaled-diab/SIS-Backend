package com.sis.service;

import com.sis.entity.BaseEntity;
import com.sis.entity.College;
import com.sis.repository.CollegeRepository;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;

import java.util.Map;
import java.util.stream.Collectors;

@AllArgsConstructor
public class CashService {

    private final CollegeRepository collegeRepository;

    @Cacheable(value = "COLLEGES")
    public Map<String, Long> cashAllColleges() {
        return collegeRepository.findAll().parallelStream().collect(Collectors.toMap(College::getCode, BaseEntity::getId));
    }
}
