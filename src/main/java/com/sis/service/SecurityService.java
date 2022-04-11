package com.sis.service;

import com.sis.dao.StudentRepository;
import com.sis.dto.BaseDTO;
import com.sis.dto.facultyMember.FacultyMemberDTO;
import com.sis.dto.security.LoginDTO;
import com.sis.dto.student.StudentDTO;
import com.sis.entity.FacultyMember;
import com.sis.entity.Student;
import com.sis.entity.mapper.FacultyMemberMapper;
import com.sis.entity.mapper.StudentMapper;
import com.sis.entity.security.User;
import com.sis.exception.InvalidUserNameOrPasswordException;
import com.sis.repository.FacultyMemberRepository;
import com.sis.repository.RoleRepository;
import com.sis.repository.UserRepository;
import com.sis.security.JwtProvider;
import com.sis.util.Constants;
import lombok.AllArgsConstructor;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collections;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SecurityService {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;
    private final StudentRepository studentRepository;
    private final FacultyMemberRepository facultyMemberRepository;
    private final StudentMapper studentMapper;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final FacultyMemberMapper facultyMemberMapper;

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

    public ResponseEntity<BaseDTO> login(LoginDTO loginDto) {
        User user = userRepository.findByEmailOrUsername(loginDto.getUsername(), loginDto.getUsername())
                .orElseThrow(InvalidUserNameOrPasswordException::new);
        generateToken(loginDto, user);
        return collectUserData(user);
    }

    private ResponseEntity<BaseDTO> collectUserData(User user) {
        if (user.getType().equals(Constants.TYPE_STUDENT)) {
            return new ResponseEntity<>(studentMapper.toDTO(studentRepository.findByUser_Id(user.getId()).orElse(new Student())), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(facultyMemberMapper.toDTO(facultyMemberRepository.findByUser_Id(user.getId()).orElse(new FacultyMember())), HttpStatus.OK);
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

    public ResponseEntity<StudentDTO> registerBulkStudents(MultipartFile file) {
        try (XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream())) {
            XSSFSheet sheet = workbook.getSheetAt(0);

        } catch (Exception e) {

        }
        return null;
    }
}
