package com.sis.controller;

import com.sis.dto.ClassroomDTO;
import com.sis.entity.Classroom;
import com.sis.entity.mapper.ClassroomMapper;
import com.sis.service.ClassroomService;
import com.sis.util.MessageResponse;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

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
}
