package com.sis.controller;

import com.sis.dto.timetable.TimetableDTO;
import com.sis.dto.timetable.TimetableRequestDTO;
import com.sis.entities.Timetable;
import com.sis.entities.mapper.TimetableMapper;
import com.sis.service.TimetableService;
import com.sis.util.MessageResponse;
import com.sis.util.PageQueryUtil;
import com.sis.util.PageResult;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/timetables")
@CrossOrigin(origins = ("*"))
@AllArgsConstructor
@Validated
public class TimetableController extends BaseController<Timetable, TimetableDTO> {

    private final TimetableService timetableService;
    private final TimetableMapper timetableMapper;

    @RequestMapping(value = "/filter/{pageNumber}/{size}", method = RequestMethod.POST)
    public ResponseEntity<PageResult<TimetableDTO>> filter(@PathVariable int pageNumber,
                                                           @PathVariable int size,
                                                           @RequestBody TimetableRequestDTO timetableRequestDTO) {
        PageQueryUtil pageUtil = new PageQueryUtil(pageNumber, size);
        return new ResponseEntity<>(timetableService.filter(pageUtil, timetableRequestDTO), HttpStatus.OK);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public MessageResponse update(@RequestBody @Valid TimetableDTO dto) {
        timetableService.save(timetableMapper.toEntity(dto));
        return new MessageResponse("Item has been updated successfully");
    }

    @RequestMapping(value = "/saveAll", method = RequestMethod.PUT)
    public MessageResponse saveAll(@RequestBody @Valid List<TimetableDTO> dtos) {
        timetableService.saveAll(timetableMapper.toEntities(dtos));
        return new MessageResponse("Item has been updated successfully");
    }
}
