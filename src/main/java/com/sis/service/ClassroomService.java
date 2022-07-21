package com.sis.service;

import com.sis.dto.ClassroomDTO;
import com.sis.entity.Building;
import com.sis.entity.Classroom;
import com.sis.entity.mapper.ClassroomMapper;
import com.sis.repository.ClassroomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassroomService extends BaseServiceImp<Classroom> {

    @Autowired
    private ClassroomRepository classroomrepository;

    @Autowired
    private BuildingService buildingService;

    @Autowired
    private ClassroomMapper classroomMapper;

    @Override
    public JpaRepository<Classroom, Long> Repository() {
        return classroomrepository;
    }

    public List<ClassroomDTO> getClassroomsByBuildingId(Long buildingId) {
        Building building = this.buildingService.findById(buildingId);
        return this.classroomMapper.toDTOs(this.classroomrepository.getClassroomsByBuildingId(building.getId()));
    }
}
