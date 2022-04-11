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
    public ArrayList<LectureTypeDTO> toDTOs(Collection<LectureType> entity) {
        return entity.stream().map(this::toDTO).collect(toCollection(ArrayList<LectureTypeDTO>::new));
    }

    @Override
    public PageResult<LectureTypeDTO> toDataPage(PageResult<LectureType> entity) {
        return new PageResult<>(entity.getData().stream().map(this::toDTO).collect(toCollection(ArrayList<LectureTypeDTO>::new)), entity.getTotalCount(), entity.getPageSize(), entity.getCurrPage());
    }

    @Override
    public ArrayList<LectureType> toentity(Collection<LectureTypeDTO> dtos) {
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
