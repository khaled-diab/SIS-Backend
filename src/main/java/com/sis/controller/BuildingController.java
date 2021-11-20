package com.sis.controller;

import com.sis.dto.building.BuildingDTO;
import com.sis.entities.Building;
import com.sis.entities.mapper.BuildingMapper;
import com.sis.service.BuildingService;
import com.sis.util.MessageResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/building")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class BuildingController extends BaseController<Building, BuildingDTO> {

    private final BuildingService buildingService;
    private final BuildingMapper buildingMapper;

    @RequestMapping(value="/addOrUpdate", method = RequestMethod.PUT)
    public MessageResponse addOrUpdate(@RequestBody BuildingDTO dto) {
        buildingService.save(buildingMapper.toEntity(dto));
        System.out.println("entered");
        return new MessageResponse("Item has been saved successfully");
    }
}
