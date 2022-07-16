package com.sis.service;

import com.sis.dto.building.BuildingDTO;
import com.sis.entity.Building;
import com.sis.entity.College;
import com.sis.entity.mapper.BuildingMapper;
import com.sis.repository.BuildingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BuildingService extends BaseServiceImp<Building> {

    @Autowired
    private BuildingRepository buildingrepository;

    @Autowired
    private BuildingMapper buildingMapper;

    @Autowired
    private CollegeService collegeService;

    @Override
    public JpaRepository<Building, Long> Repository() {
        return buildingrepository;
    }

    public List<BuildingDTO> getBuildingsByCollegeId(Long collegeId){
        College college = this.collegeService.findById(collegeId);
        return this.buildingMapper.toDTOs(this.buildingrepository.getBuildingsByCollegeId_Id(college.getId()));
    }
}
