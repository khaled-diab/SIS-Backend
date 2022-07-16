package com.sis.repository;

import com.sis.entity.Building;

import java.util.List;

public interface BuildingRepository extends BaseRepository<Building> {
    List<Building> getBuildingsByCollegeId_Id(Long collegeId);
}
