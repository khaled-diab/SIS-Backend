package com.sis.controller;

import com.sis.dto.facultyMember.FacultyMemberDTO;
import com.sis.dto.facultyMember.FacultyMemberRequestDTO;
import com.sis.dto.facultyMember.FacultyMemberTableRecordsDTO;
import com.sis.entity.FacultyMember;
import com.sis.entity.mapper.FacultyMemberMapper;
import com.sis.exception.FacultyMemberFieldNotUniqueException;
import com.sis.service.FacultyMemberService;
import com.sis.util.MessageResponse;
import com.sis.util.PageQueryUtil;
import com.sis.util.PageResult;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Validated
@RequestMapping(value = "/api/facultyMembers")
@AllArgsConstructor
public class FacultyMemberController extends BaseController<FacultyMember, FacultyMemberDTO> {


    private final FacultyMemberService facultyMemberService;

    private final FacultyMemberMapper facultyMemberMapper;

    @RequestMapping(value = "/search/{pageNumber}/{size}", method = RequestMethod.POST)
    public ResponseEntity<PageResult<FacultyMemberDTO>> search(@PathVariable int pageNumber,
                                                               @PathVariable int size,
                                                               @RequestBody FacultyMemberRequestDTO
                                                                       facultyMemberRequestDTO) {
        PageQueryUtil pageUtil = new PageQueryUtil(pageNumber, size);
        return new ResponseEntity<>(facultyMemberService.search(pageUtil, facultyMemberRequestDTO), HttpStatus.OK);
    }

    @RequestMapping(value = "/saveFacultyMember", method = RequestMethod.POST)
    public MessageResponse update(@RequestBody @Valid FacultyMemberDTO dto) {
        FacultyMember facultyMemberByNationalID = this.facultyMemberService.findByNationalID(dto.getNationalID());
        FacultyMember facultyMemberByUniversityMail = this.facultyMemberService.findByUniversityMail(dto.getUniversityMail());
        FacultyMember facultyMemberByPhone = this.facultyMemberService.findByPhone(dto.getPhone());
        if (facultyMemberByNationalID != null && facultyMemberByNationalID.getId() != dto.getId()) {
            throw new FacultyMemberFieldNotUniqueException("nationalId", "NationalID Id Already Exists");
        }
        if (facultyMemberByUniversityMail != null && facultyMemberByUniversityMail.getId() != dto.getId()) {
            throw new FacultyMemberFieldNotUniqueException("universityMail", "University Mail Already Exists");
        }
        if (facultyMemberByPhone != null && facultyMemberByPhone.getId() != dto.getId()) {
            throw new FacultyMemberFieldNotUniqueException("phone", "Phone Number Already Exists");
        }
        facultyMemberService.save(facultyMemberMapper.toEntity(dto));
        return new MessageResponse("Item has been updated successfully");
    }

    @RequestMapping(value = "/filter/{pageNumber}/{size}", method = RequestMethod.POST)
    public ResponseEntity<PageResult<FacultyMemberTableRecordsDTO>> filter(@PathVariable int pageNumber,
                                                                           @PathVariable int size,
                                                                           @RequestBody FacultyMemberRequestDTO
                                                                                   facultyMemberRequestDTO) {
        PageQueryUtil pageUtil = new PageQueryUtil(pageNumber, size);
        return new ResponseEntity<>(facultyMemberService.filter(pageUtil, facultyMemberRequestDTO), HttpStatus.OK);
    }

    @RequestMapping(value = "/facultyMembersByCollegeId/{collegeId}", method = RequestMethod.GET)
    public ResponseEntity<List<FacultyMemberDTO>> facultyMembersByCollegeId(@PathVariable long collegeId) {
        List<FacultyMemberDTO> facultyMemberDTOs = this.facultyMemberService.getFacultyMembersByCollegeId(collegeId);
        return new ResponseEntity<>(facultyMemberDTOs, HttpStatus.OK);
    }
}
