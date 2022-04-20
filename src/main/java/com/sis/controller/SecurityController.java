package com.sis.controller;

import com.sis.dto.BaseDTO;
import com.sis.dto.facultyMember.FacultyMemberDTO;
import com.sis.dto.security.LoginDTO;
import com.sis.dto.student.StudentDTO;
import com.sis.service.SecurityService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = "/api/security")
@AllArgsConstructor
public class SecurityController {
    private final SecurityService securityService;

    @PostMapping(value = "/register-student")
    public ResponseEntity<StudentDTO> registerStudent(@RequestBody StudentDTO studentDTO) {
        return securityService.registerStudent(studentDTO);
    }

    @PostMapping(value = "/register-bulk-students", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<StudentDTO> registerBulkStudents(@RequestParam("file") MultipartFile file) {
        return securityService.registerBulkStudents(file);
    }

    @PostMapping(value = "/register-faculty-member")
    public ResponseEntity<FacultyMemberDTO> createFacultyMember(@RequestBody final FacultyMemberDTO facultyMemberDTO) {
        return securityService.registerFacultyMember(facultyMemberDTO);
    }

    @PostMapping(value = "/sign-in")
    public ResponseEntity<BaseDTO> login(@RequestBody final LoginDTO loginDto) {
        return securityService.login(loginDto);
    }
}
