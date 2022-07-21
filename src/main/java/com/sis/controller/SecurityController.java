package com.sis.controller;

import com.sis.dto.BaseDTO;
import com.sis.dto.UserFileDto;
import com.sis.dto.college.GeneralSearchRequest;
import com.sis.dto.security.LoginDTO;
import com.sis.dto.security.ProfilePassword;
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

import static com.sis.util.Constants.TYPE_STAFF;
import static com.sis.util.Constants.TYPE_STUDENT;

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
    public MessageResponse createFacultyMember(@RequestBody final RegisterDTO registerDTO) {
        return securityService.registerFacultyMember(registerDTO);
    }

    @PostMapping(value = "/register-bulk-students", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public MessageResponse registerBulkStudents(@RequestParam("file") MultipartFile file) {
        return securityService.registerBulkUsers(file, TYPE_STUDENT);
    }

    @PostMapping(value = "/register-bulk-faculty-member", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public MessageResponse registerBulkFacultyMember(@RequestParam("file") MultipartFile file) {
        return securityService.registerBulkUsers(file, TYPE_STAFF);
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

    @PostMapping(value = "/changePassword")
    public ResponseEntity<MessageResponse> changePassword(@RequestBody ProfilePassword profilePassword) {
        if(!securityService.changePassword(profilePassword)){
            return new ResponseEntity<>(new MessageResponse("Password updated Failed"),HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new MessageResponse("Password updated Successfully"),HttpStatus.OK);
    }
}
