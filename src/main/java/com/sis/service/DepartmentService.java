package com.sis.service;

import com.sis.entity.BaseEntity;
import com.sis.entity.Department;
import com.sis.repository.Departmentrepository;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DepartmentService extends BaseServiceImp<Department> {
    private final Departmentrepository departmentrepository;
    private final RedisTemplate<String, Map<String, Long>> redisTemplate;

//    @PostConstruct
//    public void init() {
//        this.cashAllDepartments();
//    }

    @Cacheable(value = "DEPARTMENTS")
    public Map<String, Long> cashAllDepartments() {
        return departmentrepository.findAll().parallelStream().collect(Collectors.toMap(Department::getCode, BaseEntity::getId));
    }

    @Override
    public JpaRepository<Department, Long> Repository() {
        return departmentrepository;
    }
}
