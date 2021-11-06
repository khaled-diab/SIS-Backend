package com.sis.entities.mapper;

import com.sis.dto.BuildingDTO;
import com.sis.dto.ClassroomDTO;
import com.sis.entities.Classroom;
import com.sis.util.PageResult;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

import static java.util.stream.Collectors.toCollection;

@Component
@AllArgsConstructor
public class ClassroomMapper implements Mapper<Classroom, ClassroomDTO> {

    private final BuildingMapper buildingMapper;

    @Override
    public ArrayList<ClassroomDTO> toDTOs(Collection<Classroom> entities) {
        return entities.stream().map(this::toDTO).collect(toCollection(ArrayList<ClassroomDTO>::new));
    }
    @Override
    public PageResult<ClassroomDTO> toDataPage(PageResult<Classroom> entities) {
        return new PageResult<>(entities.getData().stream().map(this::toDTO).collect(toCollection(ArrayList<ClassroomDTO>::new)), entities.getTotalCount(), entities.getPageSize(), entities.getCurrPage());
    }
    @Override
    public ArrayList<Classroom> toEntities(Collection<ClassroomDTO> dtos) {
        return dtos.stream().map(this::toEntity).collect(toCollection(ArrayList<Classroom>::new));
    }

    @Override
    public ClassroomDTO toDTO(Classroom entity) {
        BuildingDTO buildingDTO = buildingMapper.toDTO(entity.getBuildingId());
        buildingDTO.setClassroomDTOCollection(null);
        ClassroomDTO dto=new ClassroomDTO();
        dto.setId(entity.getId());
        dto.setCode(entity.getCode());
        dto.setName_ar(entity.getName_ar());
        dto.setName_en(entity.getName_en());
        dto.setStatus(entity.getStatus());
        dto.setCapacity(entity.getCapacity());
        dto.setBuildingDTO(buildingDTO);
        dto.setDepartmentID(entity.getDepartmentID());
        return dto;
    }

    @Override
    public Classroom toEntity(ClassroomDTO dto) {
        Classroom entity=new Classroom();
        entity.setId(dto.getId());
        entity.setCode(dto.getCode());
        entity.setName_ar(dto.getName_ar());
        entity.setName_en(dto.getName_en());
        entity.setStatus(dto.getStatus());
        entity.setCapacity(dto.getCapacity());
        entity.setDepartmentID(dto.getDepartmentID());
        entity.setBuildingId(buildingMapper.toEntity(dto.getBuildingDTO()));
        return entity;
    }

}
