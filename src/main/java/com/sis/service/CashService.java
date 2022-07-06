package com.sis.service;

import com.sis.dto.DepartmentProjection;
import com.sis.dto.college.CollegeProjection;
import com.sis.repository.CollegeRepository;
import com.sis.repository.DepartmentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class CashService {

    private final CollegeRepository collegeRepository;

    private final DepartmentRepository departmentRepository;

    //    @Cacheable(value = "COLLEGES")
    public Map<String, List<CollegeProjection>> cashAllColleges() {
        return collegeRepository.findAllIdsAndCodes().parallelStream().collect(Collectors.groupingBy(CollegeProjection::getCode));
    }

    //    @Cacheable(value = "DEPARTMENTS")
    public Map<Long, List<DepartmentProjection>> cashAllDepartments() {
        return departmentRepository.findAllIdsAndCodes().parallelStream().collect(Collectors.groupingBy(DepartmentProjection::getCollegeId));
    }

}
