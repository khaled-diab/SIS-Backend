package com.sis.controller;

import com.sis.entities.mapper.FacultyMemberMapper;
import com.sis.service.BaseServiceImp;
import com.sis.service.FacultyMemberService;
import com.sis.util.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.sis.dto.FacultyMemberDTO;
import com.sis.entities.FacultyMember;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/facultyMember")
public class FacultyMemberController extends BaseController<FacultyMember, FacultyMemberDTO>{

    BaseServiceImp baseServiceImp;

    @Autowired
    FacultyMemberService facultyMemberService;

    @Autowired
    FacultyMemberMapper facultyMemberMapper;

    @RequestMapping(value = "/up/", method = RequestMethod.PUT)
    public MessageResponse up(@RequestBody FacultyMemberDTO dto) {
        facultyMemberService.update(dto);
        return new MessageResponse("Item has been updated successfully");
    }

    @RequestMapping(value = "/filterBy", method = RequestMethod.GET)
    public List<FacultyMemberDTO> filterBy(@RequestParam Map<String, String> params) {
        return facultyMemberMapper.toDTOs(baseServiceImp.filterBy(params));
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public List<FacultyMemberDTO> search(@RequestParam(value = "key") String key) {
        return facultyMemberMapper.toDTOs(facultyMemberService.search(key));
    }

}
