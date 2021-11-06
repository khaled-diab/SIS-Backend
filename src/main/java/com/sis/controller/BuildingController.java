package com.sis.controller;

import com.sis.dto.BuildingDTO;
import com.sis.entities.Building;
import com.sis.entities.mapper.BuildingMapper;
import com.sis.entities.mapper.Mapper;
import com.sis.service.BaseServiceImp;
import com.sis.service.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/building")
public class BuildingController extends BaseController<Building, BuildingDTO>{
}
