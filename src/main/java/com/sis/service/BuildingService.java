package com.sis.service;

import com.sis.dao.BuildingDao;
import com.sis.dao.FacultyRepository;
import com.sis.entities.Building;
import com.sis.entities.Faculty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BuildingService extends BaseServiceImp<Building>{
	
	@Autowired
	private  BuildingDao buildingDao;
	@Override
	public JpaRepository<Building, Long> Repository() {
		return buildingDao;
	}
}
