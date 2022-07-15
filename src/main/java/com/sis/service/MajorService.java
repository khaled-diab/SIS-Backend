package com.sis.service;

import com.sis.dto.MajorDTO;
import com.sis.entity.Department;
import com.sis.entity.Major;
import com.sis.entity.mapper.MajorMapper;
import com.sis.repository.MajorRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MajorService extends BaseServiceImp<Major> {

    private MajorRepository majorRepository;
    private MajorMapper majorMapper;
    private final DepartmentService departmentService;

    @Override
    public JpaRepository<Major, Long> Repository() {
        return majorRepository;
    }

    public List<MajorDTO> getMajorsByDepartmentId(Long departmentId) {
        Department department = this.departmentService.findById(departmentId);
        return this.majorMapper.toDTOs(this.majorRepository.getMajorsByDepartment_Id(department.getId()));
    }
}
