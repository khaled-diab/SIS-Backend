package com.sis.controller;

import com.sis.dto.facultyMember.FacultyMemberDTO;
import com.sis.dto.facultyMember.FacultyMemberRequestDTO;
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
@RequestMapping(value = "/api/facultyMembers")
@CrossOrigin(origins = "http://localhost:4200")
@AllArgsConstructor
public class FacultyMemberController extends BaseController<FacultyMember, FacultyMemberDTO> {


    private final FacultyMemberService facultyMemberService;
    private final FacultyMemberMapper facultyMemberMapper;

    @RequestMapping(value = "/search/{pageNumber}/{size}", method = RequestMethod.POST)
    public ResponseEntity<PageResult<FacultyMemberDTO>> search(@PathVariable int pageNumber,
                                                               @PathVariable int size,
                                                               @RequestBody FacultyMemberRequestDTO facultyMemberRequestDTO) {
        PageQueryUtil pageUtil = new PageQueryUtil(pageNumber, size);
        return new ResponseEntity<>(facultyMemberService.search(pageUtil, facultyMemberRequestDTO), HttpStatus.OK);
    }

    @RequestMapping(value = "/saveFacultyMember", method = RequestMethod.PUT)
    public MessageResponse update(@RequestBody FacultyMemberDTO dto) {
        facultyMemberService.save(facultyMemberMapper.toEntity(dto));
        return new MessageResponse("Item has been updated successfully");
    }

}
