package com.sis.entities.mapper;

import com.sis.dto.CollegeDTO;
import com.sis.entities.College;
import com.sis.util.PageResult;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

import static java.util.stream.Collectors.toCollection;

@Component
public class CollegeMapper implements Mapper<College,CollegeDTO>{



    @Override
    public CollegeDTO toDTO(College entity) {

        CollegeDTO dto = new CollegeDTO();
        dto.setCode(entity.getCode());
        dto.setNameAr(entity.getNameAr());
        dto.setNameEn(entity.getNameEn());
        dto.setId(entity.getId());
        return dto;
    }

    @Override
    public College toEntity(CollegeDTO dto) {

        College entity =new College();
        entity.setCode(dto.getCode());
        entity.setNameAr(dto.getNameAr());
        entity.setNameEn(dto.getNameEn());
        entity.setId(dto.getId());
        return entity;

    }

    @Override
    public ArrayList<CollegeDTO> toDTOs(Collection<College> Colleges) {
        return Colleges.stream().map(entity -> toDTO(entity)).collect(toCollection(ArrayList<CollegeDTO>::new));
    }

    @Override
    public ArrayList<College> toEntities(Collection<CollegeDTO> collegeDTOS) {
        return collegeDTOS.stream().map(dto -> toEntity(dto)).collect(toCollection(ArrayList<College>::new));
    }

    @Override
    public PageResult<CollegeDTO> toDataPage(PageResult<College> entities) {
        return new PageResult<>(entities.getData().stream().map(entity -> toDTO(entity)).collect(toCollection(ArrayList<CollegeDTO>::new)), entities.getTotalCount(), entities.getPageSize(), entities.getCurrPage());

    }

}
