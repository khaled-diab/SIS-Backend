package com.sis.service;

import com.sis.entity.Building;
import com.sis.repository.Buildingrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class BuildingService extends BaseServiceImp<Building> {

    @Autowired
    private Buildingrepository buildingrepository;

    @Override
    public JpaRepository<Building, Long> Repository() {
        return buildingrepository;
    }
}
