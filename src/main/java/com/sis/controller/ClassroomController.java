package com.sis.controller;

import com.sis.dto.ClassroomDTO;
import com.sis.entities.Classroom;
import com.sis.entities.mapper.ClassroomMapper;
import com.sis.service.ClassroomService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/classroom")
@AllArgsConstructor
public class ClassroomController extends BaseController<Classroom, ClassroomDTO> {

    private final ClassroomService classroomService;
    private final ClassroomMapper classroomMapper;


    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public List<ClassroomDTO> search(@RequestParam("key") String key) {
        return classroomMapper.toDTOs(classroomService.search(key));
    }
}
