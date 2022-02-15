package com.sis.service;

import com.sis.dao.FacultyMemberRepository;
import com.sis.dao.RoleRepository;
import com.sis.dao.StudentRepository;
import com.sis.dao.UserRepository;
import com.sis.dto.StudentDTO;
import com.sis.dto.facultyMember.FacultyMemberDTO;
import com.sis.dto.security.LoginDTO;
import com.sis.entities.BaseEntity;
import com.sis.entities.Student;
import com.sis.entities.mapper.StudentMapper;
import com.sis.entities.security.User;
import com.sis.exception.InvalidUserNameOrPasswordException;
import com.sis.security.JwtProvider;
import com.sis.util.Constants;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
public class SecurityService {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;
    private final StudentRepository studentRepository;
    private final FacultyMemberRepository facultyMemberRepository;
    private final StudentMapper studentMapper;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public SecurityService(UserRepository userRepository, AuthenticationManager authenticationManager,
                           JwtProvider jwtProvider, StudentRepository studentRepository, FacultyMemberRepository facultyMemberRepository,
                           StudentMapper studentMapper, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.jwtProvider = jwtProvider;
        this.studentRepository = studentRepository;
        this.facultyMemberRepository = facultyMemberRepository;
        this.studentMapper = studentMapper;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public ResponseEntity<StudentDTO> registerStudent(StudentDTO studentDTO) {
        Student student = studentMapper.toEntity(studentDTO);
        User user = student.getUser();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(roleRepository.getRoleStudent());
        student.setUser(userRepository.save(user));
        return new ResponseEntity<>(studentMapper.toDTO(studentRepository.save(student)), HttpStatus.OK);
    }

    public ResponseEntity<FacultyMemberDTO> registerFacultyMember(FacultyMemberDTO facultyMemberDTO) {
        return null;
    }

    public ResponseEntity<BaseEntity> login(LoginDTO loginDto) {
        User user = userRepository.findByEmailOrUsername(loginDto.getUsername(), loginDto.getUsername())
                .orElseThrow(InvalidUserNameOrPasswordException::new);
        generateToken(loginDto, user);
        return collectUserData(user);
    }

    private ResponseEntity<BaseEntity> collectUserData(User user) {
        if (user.getType().equals(Constants.TYPE_STUDENT)) {
            return new ResponseEntity<>(studentRepository.findByUser_Id(user.getId()).orElse(null), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(facultyMemberRepository.findByUser_Id(user.getId()).orElse(null), HttpStatus.OK);
        }
    }

    private void generateToken(LoginDTO loginDto, User user) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));
        } catch (AuthenticationException authenticationException) {
            throw new InvalidUserNameOrPasswordException();
        }
        user.setToken(Optional.of(jwtProvider.createToken(user.getUsername(), Collections.singletonList(user.getRole())))
                .orElseThrow(InvalidUserNameOrPasswordException::new));
    }
}
