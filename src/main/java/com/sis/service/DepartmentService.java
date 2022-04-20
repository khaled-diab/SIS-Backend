package com.sis.service;

import com.sis.entity.BaseEntity;
import com.sis.entity.Department;
import com.sis.repository.Departmentrepository;
import com.sis.util.Constants;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DepartmentService extends BaseServiceImp<Department> {
    private final Departmentrepository departmentrepository;
    private final RedisTemplate<String, Map<String, Long>> redisTemplate;

    @PostConstruct
    public void init() {
        this.cashAllDepartments();
    }

    private void cashAllDepartments() {
        Map<String, Long> collect = departmentrepository.findAll().parallelStream().collect(Collectors.toMap(Department::getCode, BaseEntity::getId));
        redisTemplate.opsForSet().add(Constants.DEPARTMENTS_CASH_KEY, collect);
    }

    @Override
    public JpaRepository<Department, Long> Repository() {
        return departmentrepository;
    }
}
