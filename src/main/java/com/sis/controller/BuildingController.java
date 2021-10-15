package com.sis.controller;

import com.sis.dto.BuildingDTO;
import com.sis.entities.Building;
import com.sis.entities.mapper.Mapper;
import com.sis.service.BaseServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/building")
public class BuildingController extends BaseController<Building, BuildingDTO>{
    @Autowired
    private BaseServiceImp<Building> baseService;
    @Autowired
    private Mapper<Building, BuildingDTO> mapper;

    @RequestMapping(value="/search", method = RequestMethod.GET)
    public List<BuildingDTO> list(@RequestParam("key") String key) {
        return mapper.toDTOs(baseService.find(key));
    }
}
