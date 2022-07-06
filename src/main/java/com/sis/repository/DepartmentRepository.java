package com.sis.repository;

import com.sis.dto.DepartmentProjection;
import com.sis.entity.Department;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DepartmentRepository extends BaseRepository<Department> {

    @Query(value = " select id,  code,college_id as collegeId from department", nativeQuery = true)
    List<DepartmentProjection> findAllIdsAndCodes();

    Department findByCode(String code);
}
