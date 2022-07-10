package com.sis.service;

import com.sis.dto.AdminDto;
import com.sis.dto.BaseDTO;
import com.sis.dto.DepartmentProjection;
import com.sis.dto.college.CollegeProjection;
import com.sis.dto.facultyMember.FacultyMemberDTO;
import com.sis.dto.security.LoginDTO;
import com.sis.dto.security.RegisterDTO;
import com.sis.dto.student.StudentDTO;
import com.sis.dto.student.StudentUploadDto;
import com.sis.entity.College;
import com.sis.entity.Department;
import com.sis.entity.FacultyMember;
import com.sis.entity.Student;
import com.sis.entity.mapper.FacultyMemberMapper;
import com.sis.entity.mapper.StudentMapper;
import com.sis.entity.mapper.UserMapper;
import com.sis.entity.security.User;
import com.sis.exception.InvalidUserNameOrPasswordException;
import com.sis.repository.*;
import com.sis.security.JwtProvider;
import com.sis.util.Constants;
import com.sis.util.MessageResponse;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
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
    private final UserMapper userMapper;
    private final CashService cashService;
    private final CollegeRepository collegeRepository;
    private final DepartmentRepository departmentRepository;
    private final PojoValidationService validationService;
    private final ExcelFileGenerator excelFileGenerator;

    private final UploadFilesService uploadFilesService;

    private final WebSocketService webSocketService;


    public ResponseEntity<StudentDTO> registerStudent1(StudentDTO studentDTO) {
        Student student = studentMapper.toEntity(studentDTO);
        User user = student.getUser();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(roleRepository.getRoleStudent());
        student.setUser(userRepository.save(user));
        return new ResponseEntity<>(studentMapper.toDTO(studentRepository.save(student)), HttpStatus.OK);
    }

    public MessageResponse registerStudent(RegisterDTO registerDTO) {
        CompletableFuture<Boolean> nationalIDExist = CompletableFuture.supplyAsync(() -> studentRepository.existsByNationalId(registerDTO.getNationalityID()));
        CompletableFuture<Boolean> mailExist = CompletableFuture.supplyAsync(() -> studentRepository.existsByUniversityMail(registerDTO.getEmail()));
        CompletableFuture.allOf(nationalIDExist, mailExist);
        if (!Boolean.TRUE.equals(nationalIDExist.join())) {
            return new MessageResponse(500, "Your National ID is not in the system. contact The administrator", null);
        } else {
            Student student = studentRepository.findByNationalId(registerDTO.getNationalityID());
            if (Boolean.TRUE.equals(mailExist.join()) || student.getUniversityMail() != null) {
                return new MessageResponse(500, "Already registered go to login", null);
            } else if (Boolean.TRUE.equals(!mailExist.join())) {
                return registerStudent(registerDTO, student);
            } else {
                return new MessageResponse(500, "Your National ID is not in the system. contact The administrator", null);
            }
        }

    }

    private MessageResponse registerStudent(RegisterDTO registerDTO, Student student) {
        User user = User.builder()
                .firstname(student.getNameEn())
                .email(registerDTO.getEmail())
                .username(registerDTO.getEmail())
                .password(passwordEncoder.encode(registerDTO.getPassword()))
                .role(roleRepository.getRoleStudent())
                .type(Constants.TYPE_STUDENT).build();
        student.setBirthDate(registerDTO.getBirthDate());
        student.setUser(userRepository.save(user));
        student.setUniversityMail(registerDTO.getEmail());
        studentRepository.save(student);
        return MessageResponse.builder().message("registered Successfully").build();
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
        } else if (user.getType().equals(Constants.TYPE_FACULTY_MEMBER)) {
            return new ResponseEntity<>(facultyMemberMapper.toDTO(facultyMemberRepository.findByUser_Id(user.getId()).orElse(new FacultyMember())), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new AdminDto(userMapper.toDTO(user)), HttpStatus.OK);
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

    public MessageResponse registerBulkStudents(MultipartFile file) {
        CompletableFuture.runAsync(() -> {
            try (XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream())) {
                Sheet sheet = workbook.getSheetAt(0);
                sheet.removeRow(sheet.iterator().next());
                List<Row> rows = StreamSupport.stream(sheet.spliterator(), true).collect(Collectors.toList());
                Map<String, List<CollegeProjection>> collegesMap = cashService.cashAllColleges();
                Map<Long, List<DepartmentProjection>> departmentsMap = cashService.cashAllDepartments();
                Map<Boolean, List<StudentUploadDto>> studentUploadMap = processRows(rows, collegesMap, departmentsMap);
                saveValidUsers(studentUploadMap.get(Boolean.TRUE));
                uploadInvalidUserToDrive(studentUploadMap.get(Boolean.FALSE), file.getOriginalFilename());
                webSocketService.sendUploadDoneNotification();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        return new MessageResponse(200, "Students are being Registered", null);
    }

    private Map<Boolean, List<StudentUploadDto>> processRows(List<Row> rows, Map<String, List<CollegeProjection>> collegesMap, Map<Long, List<DepartmentProjection>> departmentsMap) {
        return rows.stream().map(row -> {
            StudentUploadDto studentUploadDto = StudentUploadDto.builder()
                    .nameAr(row.getCell(0).getStringCellValue())
                    .nationality(row.getCell(1).getStringCellValue())
                    .nationalId(row.getCell(2).getStringCellValue())
                    .collegeCode(row.getCell(3).getStringCellValue())
                    .departmentCode(row.getCell(4).getStringCellValue())
                    .build();
            studentUploadDto.setErrors(validationService.validate(studentUploadDto).stream()
                    .map(violation -> new StringBuilder()
                            .append(Constants.FIELD).append(" -> ").append(violation.getPropertyPath()).append(System.lineSeparator())
                            .append(" ")
                            .append(Constants.ERROR).append(" -> ").append(violation.getMessage()).append(System.lineSeparator()))
                    .collect(Collectors.joining()));
            validateCollegeAndDepartmentAndNationalID(collegesMap, departmentsMap, studentUploadDto);
            studentUploadDto.setIsValid(studentUploadDto.getErrors().isEmpty());
            return studentUploadDto;
        }).collect(Collectors.groupingBy(StudentUploadDto::getIsValid));
    }

    private void validateCollegeAndDepartmentAndNationalID(Map<String, List<CollegeProjection>> collegesMap, Map<Long, List<DepartmentProjection>> departmentsMap, StudentUploadDto studentUploadDto) {
        Optional<List<CollegeProjection>> optionalListColleges = Optional.ofNullable(collegesMap.get(studentUploadDto.getCollegeCode()));
        if (optionalListColleges.isEmpty()) {
            studentUploadDto.setErrors(studentUploadDto.getErrors() + Constants.FIELD + " -> " + " college-code " + System.lineSeparator() +
                    Constants.ERROR + "-> " + "college doesnt exist" + System.lineSeparator());
        } else {
            Optional<List<DepartmentProjection>> optionalListDepartments = Optional.ofNullable(departmentsMap.get(optionalListColleges.get().get(0).getId()));
            boolean noneMatch = optionalListDepartments.get().stream().noneMatch(departmentProjection -> Objects.equals(departmentProjection.getCode(), studentUploadDto.getDepartmentCode()));
            if (noneMatch) {
                studentUploadDto.setErrors(studentUploadDto.getErrors() + Constants.FIELD + " -> " + " department-code " + System.lineSeparator() +
                        Constants.ERROR + " -> " + " department doesnt exist for the given college" + System.lineSeparator());
            }
        }
        if (Boolean.TRUE.equals(studentRepository.existsByNationalId(studentUploadDto.getNationalId()))) {
            studentUploadDto.setErrors(studentUploadDto.getErrors() + Constants.FIELD + " -> " + " national-ID " + System.lineSeparator() +
                    Constants.ERROR + " -> " + " National ID already on the system" + System.lineSeparator());
        }
    }

    private void uploadInvalidUserToDrive(List<StudentUploadDto> studentUploadDtoList, String fileName) throws IOException {
        String excelSheetEncoded = excelFileGenerator.generateInvalidStudentsExcelSheet(studentUploadDtoList);
        uploadInvalidStudents(excelSheetEncoded, fileName);
    }

    private void saveValidUsers(List<StudentUploadDto> studentUploadDtoList) {
        if (studentUploadDtoList != null) {
            College college = collegeRepository.findByCode(studentUploadDtoList.get(0).getCollegeCode());
            Department department = departmentRepository.findByCode(studentUploadDtoList.get(0).getDepartmentCode());
            studentRepository.saveAll(studentUploadDtoList
                    .parallelStream()
                    .map(studentUploadDto -> Student.builder()
                            .nationalId(studentUploadDto.getNationalId())
                            .nationality(studentUploadDto.getNationality())
                            .collegeId(college)
                            .departmentId(department)
                            .nameAr(studentUploadDto.getNameAr())
                            .level("1")
                            .build())
                    .collect(Collectors.toList()));
        }
    }


    public void uploadInvalidStudents(String file, String fileName) {
        uploadFilesService.uploadStudents(file, fileName, Constants.ADMIN_USER_NAME);
    }

    public MessageResponse uploadProfilePicture(MultipartFile file, String email) throws IOException {
        return uploadFilesService.uploadProfilePicture(Base64.getEncoder().encodeToString(file.getBytes()), file.getOriginalFilename(), email);
    }


}
