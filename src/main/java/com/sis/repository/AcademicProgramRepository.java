package com.sis.repository;

import com.sis.entity.AcademicProgram;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AcademicProgramRepository extends BaseRepository<AcademicProgram> {
    @Query(value = "select * from academic_program where department_id = :departmentId",nativeQuery = true)
    List<AcademicProgram> findAcademicProgramByDepartmentId(Long departmentId);

}
