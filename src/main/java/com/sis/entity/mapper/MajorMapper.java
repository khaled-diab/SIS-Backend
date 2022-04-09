package com.sis.entity.mapper;

import com.sis.dto.MajorDTO;
import com.sis.entity.Major;
import com.sis.util.PageResult;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

import static java.util.stream.Collectors.toCollection;

@Component
@AllArgsConstructor
public class MajorMapper implements Mapper<Major, MajorDTO> {

    private CollegeMapper collegeMapper;

    private DepartmentMapper departmentMapper;

    @Override
    public ArrayList<MajorDTO> toDTOs(Collection<Major> entities) {
        return entities.stream().map(this::toDTO).collect(toCollection(ArrayList<MajorDTO>::new));
    }

    @Override
    public PageResult<MajorDTO> toDataPage(PageResult<Major> entities) {
        return new PageResult<>(entities.getData().stream().map(this::toDTO).collect(toCollection(ArrayList<MajorDTO>::new)), entities.getTotalCount(), entities.getPageSize(), entities.getCurrPage());
    }

    @Override
    public ArrayList<Major> toEntities(Collection<MajorDTO> dtos) {
        return dtos.stream().map(this::toEntity).collect(toCollection(ArrayList<Major>::new));
    }

    @Override
    public MajorDTO toDTO(Major entity) {
        MajorDTO dto = new MajorDTO();
        dto.setId(entity.getId());
        dto.setNameAr(entity.getNameAr());
        dto.setNameEn(entity.getNameEn());
        if(entity.getCollege()!=null) {
            dto.setCollegeDTO(this.collegeMapper.toDTO(entity.getCollege()));
        }
        if(entity.getDepartment()!=null) {
            dto.setDepartmentDTO(this.departmentMapper.toDTO(entity.getDepartment()));
        }
        return dto;
    }

    @Override
    public Major toEntity(MajorDTO dto) {
        Major entity = new Major();
        entity.setId(dto.getId());
        entity.setNameAr(dto.getNameAr());
        entity.setNameEn(dto.getNameEn());
        if(dto.getCollegeDTO()!=null) {
            entity.setCollege(this.collegeMapper.toEntity(dto.getCollegeDTO()));
        }
        if(dto.getDepartmentDTO()!=null) {

            entity.setDepartment(this.departmentMapper.toEntity(dto.getDepartmentDTO()));
        }
        return entity;
    }

}
