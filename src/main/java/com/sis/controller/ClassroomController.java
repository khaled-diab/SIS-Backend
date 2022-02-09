package com.sis.controller;

import com.sis.dto.ClassroomDTO;
import com.sis.dto.building.BuildingDTO;
import com.sis.entities.Classroom;
import com.sis.entities.mapper.ClassroomMapper;
import com.sis.service.ClassroomService;
import com.sis.util.MessageResponse;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/classroom")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@Validated
public class ClassroomController extends BaseController<Classroom, ClassroomDTO> {
    private final ClassroomService classroomService;
    private final ClassroomMapper classroomMapper;

    @RequestMapping(value="/addOrUpdate", method = RequestMethod.PUT)
    public MessageResponse addOrUpdate(@RequestBody @Valid ClassroomDTO dto) {
        classroomService.save(classroomMapper.toEntity(dto));
        return new MessageResponse("Item has been saved successfully");
    }
}
