package com.sis.repository;

import com.sis.entity.Major;

import java.util.List;

public interface MajorRepository extends BaseRepository<Major> {

    List<Major> getMajorsByDepartment_Id(Long departmentId);

}
