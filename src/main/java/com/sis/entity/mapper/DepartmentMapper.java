package com.sis.entity.mapper;


import com.sis.dto.DepartmentDTO;
import com.sis.entity.College;
import com.sis.entity.Department;
import com.sis.service.CollegeService;
import com.sis.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

import static java.util.stream.Collectors.toCollection;

@Component
public class DepartmentMapper implements Mapper<Department,DepartmentDTO>{

    @Autowired
    private CollegeService collegeService ;

    @Autowired
    private CollegeMapper collegeMapper;

    @Override
    public DepartmentDTO toDTO(Department entity) {
        DepartmentDTO dto = new DepartmentDTO();
        dto.setId(entity.getId());
        dto.setCode(entity.getCode());
        dto.setNameAr(entity.getNameAr());
        dto.setNameEn(entity.getNameEn());
        dto.setId(entity.getId());
        if(entity.getCollegeId()!=null) {
            dto.setCollegeDTO(collegeMapper.toDTO(entity.getCollegeId()));
        }
        return dto;
    }

    @Override
    public Department toEntity(DepartmentDTO dto) {
        Department department = new Department();
        if(dto!=null) {

            department.setCode(dto.getCode());
            department.setNameAr(dto.getNameAr());
            department.setNameEn(dto.getNameEn());
            department.setId(dto.getId());

            if (dto.getCollegeDTO() != null) {
                College college = collegeService.findById(dto.getCollegeDTO().getId());
                department.setCollegeId(college);
                department.setAcademicProgramCollection(null);
                department.setFacultyCollection(null);
                department.setStudentCollection(null);
            }
        }
        return  department;
    }

    @Override
    public ArrayList<DepartmentDTO> toDTOs(Collection<Department> departements) {
        return departements.stream().map(entity -> toDTO(entity)).collect(toCollection(ArrayList<DepartmentDTO>::new));
    }

    @Override
    public ArrayList<Department> toEntities(Collection<DepartmentDTO> departmentDTOS) {
        return departmentDTOS.stream().map(dto -> toEntity(dto)).collect(toCollection(ArrayList<Department>::new));
    }

    @Override
    public PageResult<DepartmentDTO> toDataPage(PageResult<Department> pageResult) {
        return new PageResult<>(pageResult.getData().stream().map(entity -> toDTO(entity)).collect(toCollection(ArrayList<DepartmentDTO>::new)), pageResult.getTotalCount(), pageResult.getPageSize(), pageResult.getCurrPage());

    }


}
