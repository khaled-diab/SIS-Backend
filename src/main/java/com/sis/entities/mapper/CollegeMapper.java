package com.sis.entities.mapper;


import com.sis.dto.college.CollegeDTO;
import com.sis.entities.College;
import com.sis.util.PageResult;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

@Component
public class CollegeMapper implements Mapper<College, CollegeDTO> {
    @Override
    public CollegeDTO toDTO(College entity) {
        CollegeDTO collegeDto = CollegeDTO.builder()
                .code(entity.getCode())
                .nameAr(entity.getNameAr())
                .nameEn(entity.getNameEn())
                .build();
        collegeDto.setId(entity.getId());
        return collegeDto;
    }

    @Override
    public College toEntity(CollegeDTO dto) {
        College college  = College.builder()
                .code(dto.getCode())
                .nameAr(dto.getNameAr())
                .nameEn(dto.getNameEn())
                .build();
        college.setId(dto.getId());
        return  college;
    }

    @Override
    public ArrayList<CollegeDTO> toDTOs(Collection<College> colleges) {
        return (ArrayList<CollegeDTO>) colleges.
                stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ArrayList<College> toEntities(Collection<CollegeDTO> collegeDTOS) {
        return (ArrayList<College>) collegeDTOS.
                stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }

    @Override
    public PageResult<CollegeDTO> toDataPage(PageResult<College> entities) {
        return new PageResult<>(toDTOs(entities.getData()), entities.getTotalCount(), entities.getPageSize(), entities.getCurrPage());
    }
}
