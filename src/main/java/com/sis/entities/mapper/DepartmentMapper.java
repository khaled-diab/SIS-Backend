package com.sis.entities.mapper;

import com.sis.dto.DepartmentDTO;
import com.sis.entities.Department;
import com.sis.util.PageResult;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

import static java.util.stream.Collectors.toCollection;

@Component

public class DepartmentMapper implements Mapper<Department,DepartmentDTO>{



    @Override
    public DepartmentDTO toDTO(Department entity) {

        DepartmentDTO dto = new DepartmentDTO();
        dto.setCode(entity.getCode());
        dto.setNameAr(entity.getNameAr());
        dto.setNameEn(entity.getNameEn());
        dto.setId(entity.getId());
        return dto;
    }

    @Override
    public Department toEntity(DepartmentDTO dto) {

        Department entity =new Department();
        entity.setCode(dto.getCode());
        entity.setNameAr(dto.getNameAr());
        entity.setNameEn(dto.getNameEn());
        entity.setId(dto.getId());
        return entity;

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
    public PageResult<DepartmentDTO> toDataPage(PageResult<Department> entities) {
        return new PageResult<>(entities.getData().stream().map(entity -> toDTO(entity)).collect(toCollection(ArrayList<DepartmentDTO>::new)), entities.getTotalCount(), entities.getPageSize(), entities.getCurrPage());

    }

}
