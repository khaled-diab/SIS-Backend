package com.sis.service;

import com.sis.repository.CollegeRepository;
import com.sis.repository.DepartmentRepository;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class CashService {

    private final CollegeRepository collegeRepository;

    private final DepartmentRepository departmentRepository;

    @Cacheable(value = "COLLEGES")
    public Map<Object, List<Object[]>> cashAllColleges() {
        return collegeRepository.findAllIdsAndCodes().parallelStream().collect(Collectors.groupingBy(objects -> objects[0]));
    }

    @Cacheable(value = "DEPARTMENTS")
    public Map<Object, List<Object[]>> cashAllDepartments() {
        return departmentRepository.findAllIdsAndCodes().parallelStream().collect(Collectors.groupingBy(objects -> objects[1]));
    }
}
