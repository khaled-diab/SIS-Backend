package com.sis.entities.mapper;

import static java.util.stream.Collectors.toCollection;

import java.util.ArrayList;
import java.util.Collection;

import com.sis.dto.building.BuildingDTO;
import com.sis.entities.Building;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import com.sis.util.PageResult;

@Component
@AllArgsConstructor
public class BuildingMapper implements Mapper<Building, BuildingDTO> {

    private final CollegeMapper collegeMapper;

    @Override
    public ArrayList<BuildingDTO> toDTOs(Collection<Building> entities) {
        return entities.stream().map(this::toDTO).collect(toCollection(ArrayList<BuildingDTO>::new));
    }

    @Override
    public PageResult<BuildingDTO> toDataPage(PageResult<Building> entities) {
        return new PageResult<>(entities.getData().stream().map(this::toDTO).collect(toCollection(ArrayList<BuildingDTO>::new)), entities.getTotalCount(), entities.getPageSize(), entities.getCurrPage());
    }

    @Override
    public ArrayList<Building> toEntities(Collection<BuildingDTO> dtos) {
        return dtos.stream().map(this::toEntity).collect(toCollection(ArrayList<Building>::new));
    }

    @Override
    public BuildingDTO toDTO(Building entity) {
        BuildingDTO dto = new BuildingDTO();
        dto.setCollegeDTO(collegeMapper.toDTO(entity.getCollegeId()));
        dto.setId(entity.getId());
        dto.setCode(entity.getCode());
        dto.setNameAr(entity.getNameAr());
        dto.setNameEn(entity.getNameEn());
        dto.setStatus(entity.getStatus());
        return dto;
    }

    @Override
    public Building toEntity(BuildingDTO dto) {
        Building entity = new Building();
        entity.setId(dto.getId());
        entity.setCode(dto.getCode());
        entity.setNameAr(dto.getNameAr());
        entity.setNameEn(dto.getNameEn());
        entity.setStatus(dto.getStatus());
        entity.setCollegeId(collegeMapper.toEntity(dto.getCollegeDTO()));
        return entity;
    }

}
