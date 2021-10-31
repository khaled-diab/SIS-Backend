package com.sis.entities.mapper;

import com.sis.dto.AcademicProgramDTO;
import com.sis.entities.AcademicProgram;
import com.sis.util.PageResult;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

import static java.util.stream.Collectors.toCollection;

@Component
public class AcademicProgramMapper implements Mapper<AcademicProgram,AcademicProgramDTO>{



    @Override
    public AcademicProgramDTO toDTO(AcademicProgram entity) {

        AcademicProgramDTO dto = new AcademicProgramDTO();
        dto.setCode(entity.getCode());
        dto.setNameAr(entity.getNameAr());
        dto.setNameEn(entity.getNameEn());
        dto.setId(entity.getId());
        return dto;
    }

    @Override
    public AcademicProgram toEntity(AcademicProgramDTO dto) {

        AcademicProgram entity =new AcademicProgram();
        entity.setCode(dto.getCode());
        entity.setNameAr(dto.getNameAr());
        entity.setNameEn(dto.getNameEn());
        entity.setId(dto.getId());
        return entity;

    }

    @Override
    public ArrayList<AcademicProgramDTO> toDTOs(Collection<AcademicProgram> academicPrograms) {
        return academicPrograms.stream().map(entity -> toDTO(entity)).collect(toCollection(ArrayList<AcademicProgramDTO>::new));
    }

    @Override
    public ArrayList<AcademicProgram> toEntities(Collection<AcademicProgramDTO> academicProgramDTOS ) {
        return academicProgramDTOS.stream().map(dto -> toEntity(dto)).collect(toCollection(ArrayList<AcademicProgram>::new));
    }

    @Override
    public PageResult<AcademicProgramDTO> toDataPage(PageResult<AcademicProgram> entities) {
        return new PageResult<>(entities.getData().stream().map(entity -> toDTO(entity)).collect(toCollection(ArrayList<AcademicProgramDTO>::new)), entities.getTotalCount(), entities.getPageSize(), entities.getCurrPage());

    }

}
