package com.sis.entity.mapper;

import com.sis.dto.ClassroomDTO;
import com.sis.entity.Classroom;
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
    private final CollegeMapper collegeMapper;

    @Override
    public ArrayList<ClassroomDTO> toDTOs(Collection<Classroom> entity) {
        return entity.stream().map(this::toDTO).collect(toCollection(ArrayList<ClassroomDTO>::new));
    }

    @Override
    public PageResult<ClassroomDTO> toDataPage(PageResult<Classroom> entity) {
        return new PageResult<>(entity.getData().stream().map(this::toDTO).collect(toCollection(ArrayList<ClassroomDTO>::new)), entity.getTotalCount(), entity.getPageSize(), entity.getCurrPage());
    }

    @Override
    public ArrayList<Classroom> toEntities(Collection<ClassroomDTO> dtos) {
        return dtos.stream().map(this::toEntity).collect(toCollection(ArrayList<Classroom>::new));
    }

    @Override
    public ClassroomDTO toDTO(Classroom entity) {
        ClassroomDTO dto = new ClassroomDTO();
        dto.setId(entity.getId());
        dto.setCode(entity.getCode());
        dto.setName(entity.getName());
        dto.setStatus(entity.getStatus());
        dto.setCapacity(entity.getCapacity());
        dto.setBuildingDTO(buildingMapper.toDTO(entity.getBuilding()));
        dto.setCollegeDTO(collegeMapper.toDTO(entity.getCollege()));
        return dto;
    }

    @Override
    public Classroom toEntity(ClassroomDTO dto) {
        Classroom entity = new Classroom();
        entity.setId(dto.getId());
        entity.setCode(dto.getCode());
        entity.setName(dto.getName());
        entity.setStatus(dto.getStatus());
        entity.setCapacity(dto.getCapacity());
        entity.setCollege(collegeMapper.toEntity(dto.getCollegeDTO()));
        entity.setBuilding(buildingMapper.toEntity(dto.getBuildingDTO()));
        return entity;
    }

}
