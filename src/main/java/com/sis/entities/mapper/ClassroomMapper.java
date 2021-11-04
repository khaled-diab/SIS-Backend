package com.sis.entities.mapper;

import com.sis.dto.ClassroomDTO;
import com.sis.entities.Classroom;
import com.sis.util.PageResult;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

import static java.util.stream.Collectors.toCollection;

@Component
public class ClassroomMapper implements Mapper<Classroom, ClassroomDTO> {

    @Override
    public ArrayList<ClassroomDTO> toDTOs(Collection<Classroom> entities) {
        return entities.stream().map(entity -> toDTO(entity)).collect(toCollection(ArrayList<ClassroomDTO>::new));
    }
    @Override
    public PageResult<ClassroomDTO> toDataPage(PageResult<Classroom> entities) {
        return new PageResult<>(entities.getData().stream().map(entity -> toDTO(entity)).collect(toCollection(ArrayList<ClassroomDTO>::new)), entities.getTotalCount(), entities.getPageSize(), entities.getCurrPage());
    }
    @Override
    public ArrayList<Classroom> toEntities(Collection<ClassroomDTO> dtos) {
        return dtos.stream().map(dto -> toEntity(dto)).collect(toCollection(ArrayList<Classroom>::new));
    }

    @Override
    public ClassroomDTO toDTO(Classroom entity) {
        ClassroomDTO dto=new ClassroomDTO();
        dto.setId(entity.getId());
        dto.setCode(entity.getCode());
        dto.setName_ar(entity.getName_ar());
        dto.setName_en(entity.getName_en());
        dto.setStatus(entity.getStatus());
        dto.setCapacity(entity.getCapacity());
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
        return entity;
    }

}
