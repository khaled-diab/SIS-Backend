package com.sis.repository;

import com.sis.entity.Department;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DepartmentRepository extends BaseRepository<Department> {

    @Query(value = " select  code,college_id from department", nativeQuery = true)
    List<Object[]> findAllIdsAndCodes();
}
