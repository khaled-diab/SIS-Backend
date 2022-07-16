package com.sis.controller;

import com.sis.dto.building.BuildingDTO;
import com.sis.entity.Building;
import com.sis.entity.mapper.BuildingMapper;
import com.sis.service.BuildingService;
import com.sis.util.MessageResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Validated
@RequestMapping(value = "/api/building")
@AllArgsConstructor
public class BuildingController extends BaseController<Building, BuildingDTO> {

    private final BuildingService buildingService;
    private final BuildingMapper buildingMapper;

    @RequestMapping(value = "/addOrUpdate", method = RequestMethod.POST)
    public MessageResponse addOrUpdate(@Valid @RequestBody BuildingDTO dto) {
        buildingService.save(buildingMapper.toEntity(dto));
        System.out.println("entered");
        return new MessageResponse("Item has been saved successfully");
    }

    @RequestMapping(value = "/buildingsByCollegeId/{collegeId}", method = RequestMethod.GET)
    public ResponseEntity<List<BuildingDTO>> buildingsByCollegeId(@Valid @PathVariable Long collegeId) {
        List<BuildingDTO> buildingDTOS = buildingService.getBuildingsByCollegeId(collegeId);
        return new ResponseEntity<>(buildingDTOS, HttpStatus.OK);
    }
}
