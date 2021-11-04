package com.sis.controller;

import com.sis.entities.mapper.FacultyMemberMapper;
import com.sis.service.BaseServiceImp;
import com.sis.service.FacultyMemberService;
import com.sis.util.MessageResponse;
import com.sis.util.PageQueryUtil;
import com.sis.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public MessageResponse up(@RequestBody FacultyMemberDTO dto) {
        facultyMemberService.update(dto);
        return new MessageResponse("Item has been updated successfully");
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public ResponseEntity<PageResult<FacultyMemberDTO>> search(@RequestParam(value = "key") String key, @RequestBody PageQueryUtil pageUtil) {

        return new ResponseEntity<>(facultyMemberService.search(pageUtil, key), HttpStatus.OK);
    }

}
