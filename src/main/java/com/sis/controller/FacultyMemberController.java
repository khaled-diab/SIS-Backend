package com.sis.controller;

import com.sis.entities.mapper.Mapper;
import com.sis.service.BaseServiceImp;
import com.sis.service.FacultyMemberService;
import com.sis.util.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.sis.dto.FacultyMemberDTO;
import com.sis.entities.FacultyMember;

import java.util.List;

@RestController
@RequestMapping(value = "/api/facultyMember")
public class FacultyMemberController extends BaseController<FacultyMember, FacultyMemberDTO>{

    @Autowired
//    private BaseServiceImp<FacultyMember> baseService;
    private FacultyMemberService facultyMemberService;

    @Autowired
    private Mapper<FacultyMember, FacultyMemberDTO> mapper;

    @RequestMapping(value="/search/", method = RequestMethod.GET)
	public List<FacultyMemberDTO> list(@RequestParam("key") String key) {
		return mapper.toDTOs(facultyMemberService.find(key));
	}

//    @Override
    @RequestMapping(value = "/up/{id}", method = RequestMethod.PUT)
    public MessageResponse up(@PathVariable(value = "id") Long id, @RequestBody FacultyMemberDTO dto) {
        facultyMemberService.update(id, dto);
        return new MessageResponse("Item has been updated successfully");
    }

}
