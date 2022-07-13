package com.sis.controller;

import com.sis.dto.BaseDTO;
import com.sis.dto.UserFileDto;
import com.sis.dto.college.GeneralSearchRequest;
import com.sis.dto.facultyMember.FacultyMemberDTO;
import com.sis.dto.security.LoginDTO;
import com.sis.dto.security.RegisterDTO;
import com.sis.service.SecurityService;
import com.sis.util.MessageResponse;
import com.sis.util.PageResult;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping(value = "/api/security")
@AllArgsConstructor
public class SecurityController {
    private final SecurityService securityService;

    @PostMapping(value = "/register-student")
    public MessageResponse registerStudent(@RequestBody RegisterDTO registerDTO) {
        return securityService.registerStudent(registerDTO);
    }

    @PostMapping(value = "/register-faculty-member")
    public ResponseEntity<FacultyMemberDTO> createFacultyMember(@RequestBody final FacultyMemberDTO facultyMemberDTO) {
        return securityService.registerFacultyMember(facultyMemberDTO);
    }

    @PostMapping(value = "/register-bulk-students", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public MessageResponse registerBulkStudents(@RequestParam("file") MultipartFile file) {
        return securityService.registerBulkStudents(file);
    }

    @PostMapping(value = "/sign-in")
    public ResponseEntity<BaseDTO> login(@RequestBody final LoginDTO loginDto) {
        return securityService.login(loginDto);
    }

    @PostMapping(value = "/upload-profile-picture", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<MessageResponse> uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("email") String email) throws IOException {
        return new ResponseEntity<>(securityService.uploadProfilePicture(file, email), HttpStatus.OK);
    }

    @PostMapping(value = "/findAll/{page}/{size}")
    public PageResult<UserFileDto> findAll(@PathVariable Integer page, @PathVariable Integer size, @RequestBody GeneralSearchRequest generalSearchRequest) {
        return securityService.getAdminUploadedFiles(page, size, generalSearchRequest);
    }
}
