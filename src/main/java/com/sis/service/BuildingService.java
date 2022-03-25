package com.sis.service;

import com.sis.entities.Building;
import com.sis.repository.BuildingDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class BuildingService extends BaseServiceImp<Building> {

    @Autowired
    private BuildingDao buildingDao;

    @Override
    public JpaRepository<Building, Long> Repository() {
        return buildingDao;
    }
}
