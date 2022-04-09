package com.sis.entity.mapper;

import com.sis.dto.LectureTypeDTO;
import com.sis.entity.LectureType;
import com.sis.util.PageResult;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

import static java.util.stream.Collectors.toCollection;

@Component
@AllArgsConstructor
public class LectureTypeMapper implements Mapper<LectureType, LectureTypeDTO> {

    @Override
    public ArrayList<LectureTypeDTO> toDTOs(Collection<LectureType> entities) {
        return entities.stream().map(this::toDTO).collect(toCollection(ArrayList<LectureTypeDTO>::new));
    }

    @Override
    public PageResult<LectureTypeDTO> toDataPage(PageResult<LectureType> entities) {
        return new PageResult<>(entities.getData().stream().map(this::toDTO).collect(toCollection(ArrayList<LectureTypeDTO>::new)), entities.getTotalCount(), entities.getPageSize(), entities.getCurrPage());
    }

    @Override
    public ArrayList<LectureType> toEntities(Collection<LectureTypeDTO> dtos) {
        return dtos.stream().map(this::toEntity).collect(toCollection(ArrayList<LectureType>::new));
    }

    @Override
    public LectureTypeDTO toDTO(LectureType entity) {
        LectureTypeDTO dto = new LectureTypeDTO();
        dto.setId(entity.getId());
        dto.setNameAr(entity.getNameAr());
        dto.setNameEn(entity.getNameEn());

        return dto;
    }

    @Override
    public LectureType toEntity(LectureTypeDTO dto) {
        LectureType entity = new LectureType();
        entity.setId(dto.getId());
        entity.setNameAr(dto.getNameAr());
        entity.setNameEn(dto.getNameEn());

        return entity;
    }

}
