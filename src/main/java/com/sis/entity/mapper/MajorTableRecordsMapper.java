package com.sis.entity.mapper;

import com.sis.dto.major.MajorTableRecordsDTO;
import com.sis.entity.Major;
import com.sis.util.PageResult;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

import static java.util.stream.Collectors.toCollection;

@Component
@AllArgsConstructor
public class MajorTableRecordsMapper implements Mapper<Major, MajorTableRecordsDTO> {

    @Override
    public ArrayList<MajorTableRecordsDTO> toDTOs(Collection<Major> entity) {
        return entity.stream().map(this::toDTO).collect(toCollection(ArrayList<MajorTableRecordsDTO>::new));
    }

    @Override
    public PageResult<MajorTableRecordsDTO> toDataPage(PageResult<Major> entity) {
        return new PageResult<>(entity.getData().stream().map(this::toDTO).collect(toCollection(ArrayList<MajorTableRecordsDTO>::new)), entity.getTotalCount(), entity.getPageSize(), entity.getCurrPage());
    }

    @Override
    public ArrayList<Major> toEntities(Collection<MajorTableRecordsDTO> dtos) {
        return dtos.stream().map(this::toEntity).collect(toCollection(ArrayList<Major>::new));
    }

    @Override
    public MajorTableRecordsDTO toDTO(Major entity) {
        MajorTableRecordsDTO dto = new MajorTableRecordsDTO();
        dto.setId(entity.getId());
        dto.setNameAr(entity.getNameAr());
        dto.setNameEn(entity.getNameEn());
        if (entity.getDepartment() != null) {
            dto.setDepartmentName(entity.getDepartment().getNameEn());
        }
        if (entity.getCollege() != null) {
            dto.setCollegeName(entity.getCollege().getNameEn());
        }
        return dto;
    }

    @Override
    public Major toEntity(MajorTableRecordsDTO dto) {
        return null;
    }

}
