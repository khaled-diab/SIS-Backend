package com.sis.controller;

import com.sis.dto.ClassroomDTO;
import com.sis.dto.building.BuildingDTO;
import com.sis.entity.Classroom;
import com.sis.entity.mapper.ClassroomMapper;
import com.sis.service.ClassroomService;
import com.sis.util.MessageResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/classroom")
@AllArgsConstructor
@Validated
public class ClassroomController extends BaseController<Classroom, ClassroomDTO> {
    private final ClassroomService classroomService;
    private final ClassroomMapper classroomMapper;

    @RequestMapping(value = "/addOrUpdate", method = RequestMethod.POST)
    public MessageResponse addOrUpdate(@RequestBody @Valid ClassroomDTO dto) {
        classroomService.save(classroomMapper.toEntity(dto));
        return new MessageResponse("Item has been saved successfully");
    }

    @RequestMapping(value = "/classroomsByBuildingId/{buildingId}", method = RequestMethod.GET)
    public ResponseEntity<List<ClassroomDTO>> buildingsByCollegeId(@Valid @PathVariable Long buildingId) {
        List<ClassroomDTO> classroomDTOS = classroomService.getClassroomsByBuildingId(buildingId);
        return new ResponseEntity<>(classroomDTOS, HttpStatus.OK);
    }
}
