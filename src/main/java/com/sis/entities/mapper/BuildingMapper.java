package com.sis.entities.mapper;

import static java.util.stream.Collectors.toCollection;
import java.util.ArrayList;
import java.util.Collection;

import com.sis.dto.BuildingDTO;
import com.sis.entities.Building;
import org.springframework.stereotype.Component;
import com.sis.dto.BuildingDTO;
import com.sis.entities.Building;
import com.sis.util.PageResult;

@Component
public class BuildingMapper implements Mapper<Building, BuildingDTO> {

    @Override
    public ArrayList<BuildingDTO> toDTOs(Collection<Building> entities) {
        return entities.stream().map(entity -> toDTO(entity)).collect(toCollection(ArrayList<BuildingDTO>::new));
    }
    @Override
    public PageResult<BuildingDTO> toDataPage(PageResult<Building> entities) {
        return new PageResult<>(entities.getData().stream().map(entity -> toDTO(entity)).collect(toCollection(ArrayList<BuildingDTO>::new)), entities.getTotalCount(), entities.getPageSize(), entities.getCurrPage());
    }
    @Override
    public ArrayList<Building> toEntities(Collection<BuildingDTO> dtos) {
        return dtos.stream().map(dto -> toEntity(dto)).collect(toCollection(ArrayList<Building>::new));
    }

    @Override
    public BuildingDTO toDTO(Building entity) {
        BuildingDTO dto=new BuildingDTO();
        dto.setId(entity.getId());
        dto.setCode(entity.getCode());
        dto.setName_ar(entity.getName_ar());
        dto.setName_en(entity.getName_en());
        return dto;
    }

    @Override
    public Building toEntity(BuildingDTO dto) {
        Building entity=new Building();
        entity.setId(dto.getId());
        entity.setCode(dto.getCode());
        entity.setName_ar(dto.getName_ar());
        entity.setName_en(dto.getName_en());
        return entity;
    }

}
