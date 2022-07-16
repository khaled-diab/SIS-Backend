package com.sis.service;

import com.sis.dto.DepartmentProjection;
import com.sis.dto.college.CollegeProjection;
import com.sis.entity.BaseEntity;
import com.sis.entity.Degree;
import com.sis.repository.CollegeRepository;
import com.sis.repository.DegreeRepository;
import com.sis.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CashService {

    private final CollegeRepository collegeRepository;
    private final DepartmentRepository departmentRepository;

    private final DegreeRepository degreeRepository;

    //    @Cacheable(value = "COLLEGES")
    public Map<String, List<CollegeProjection>> cashAllColleges() {
        return collegeRepository.findAllIdsAndCodes().parallelStream().collect(Collectors.groupingBy(CollegeProjection::getCode));
    }

    //    @Cacheable(value = "DEPARTMENTS")
    public Map<Long, List<DepartmentProjection>> cashAllDepartments() {
        return departmentRepository.findAllIdsAndCodes().parallelStream().collect(Collectors.groupingBy(DepartmentProjection::getCollegeId));
    }

    public Map<Long, List<Degree>> degreeMap() {
        return degreeRepository.findAll().stream().collect(Collectors.groupingBy(BaseEntity::getId));
    }

}
