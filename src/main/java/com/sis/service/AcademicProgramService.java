package com.sis.service;

import com.sis.dto.AcademicProgramDTO;
import com.sis.entity.AcademicProgram;
import com.sis.entity.College;
import com.sis.entity.Department;
import com.sis.entity.mapper.AcademicProgramMapper;
import com.sis.repository.AcademicProgramRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AcademicProgramService extends BaseServiceImp<AcademicProgram> {
    @Autowired
    private AcademicProgramRepository academicProgramRepository;
    @Autowired
    private AcademicProgramMapper academicProgramMapper;
    @Autowired
    private DepartmentService departmentService;

    @Override
    public JpaRepository<AcademicProgram, Long> Repository() {
        return academicProgramRepository;
    }

    public List<AcademicProgramDTO> academicProgramsByDepartmentId(Long departmentId) {
//        Department dept = this.departmentService.findById(departmentId);
        return this.academicProgramMapper.toDTOs(this.academicProgramRepository.findAcademicProgramByDepartmentId(departmentId));
    }
}
