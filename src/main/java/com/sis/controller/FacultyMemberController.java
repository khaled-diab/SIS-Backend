package com.sis.controller;

import com.sis.dto.FacultyMemberDTO;
import com.sis.entities.FacultyMember;
import com.sis.entities.mapper.FacultyMemberMapper;
import com.sis.service.FacultyMemberService;
import com.sis.util.MessageResponse;
import com.sis.util.PageQueryUtil;
import com.sis.util.PageResult;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/facultyMember")
@AllArgsConstructor
public class FacultyMemberController extends BaseController<FacultyMember, FacultyMemberDTO> {


    private final FacultyMemberService facultyMemberService;

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
