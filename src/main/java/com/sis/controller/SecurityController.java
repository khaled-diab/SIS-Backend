package com.sis.controller;

import com.sis.dto.StudentDTO;
import com.sis.dto.facultyMember.FacultyMemberDTO;
import com.sis.dto.security.LoginDTO;
import com.sis.entities.BaseEntity;
import com.sis.service.SecurityService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/security")
@AllArgsConstructor
public class SecurityController {


    private final SecurityService securityService;


    @PostMapping(value = "/register-student")
    public ResponseEntity<StudentDTO> registerStudent(@RequestBody StudentDTO studentDTO) {
        return securityService.registerStudent(studentDTO);
    }

    @PostMapping(value = "/register-faculty-member")
    public ResponseEntity<FacultyMemberDTO> createFacultyMember(@RequestBody final FacultyMemberDTO facultyMemberDTO) {
        return securityService.registerFacultyMember(facultyMemberDTO);
    }

    @PostMapping(value = "/sign-in")
    public ResponseEntity<BaseEntity> login(@RequestBody final LoginDTO loginDto) {
        return securityService.login(loginDto);
    }
}
