package com.sis.repository;

import com.sis.entity.AcademicProgram;

import java.util.List;

public interface AcademicProgramRepository extends BaseRepository<AcademicProgram> {
    List<AcademicProgram> getAcademicProgramsByCollegeIdId(Long collegeId);

}
