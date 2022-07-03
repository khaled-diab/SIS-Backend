package com.sis.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sis.dto.AdminDto;
import com.sis.dto.BaseDTO;
import com.sis.dto.FileUploadDownLoadModel;
import com.sis.dto.facultyMember.FacultyMemberDTO;
import com.sis.dto.security.LoginDTO;
import com.sis.dto.security.RegisterDTO;
import com.sis.dto.student.StudentDTO;
import com.sis.dto.student.StudentUploadDto;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class SecurityService {

    private final ObjectMapper objectMapper;
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
    private final RestTemplate restTemplate;
    private final UserFileRepository userFileRepository;
    private final CashService cashService;

    private final CollegeRepository collegeRepository;

    private final PojoValidationService validationService;
    Logger logger = LoggerFactory.getLogger(SecurityService.class);
    @Value("${upload.picture.url}")
    private String uploadProfilePictureUrl;


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
                List<Row> rows = StreamSupport.stream(sheet.spliterator(), false).collect(Collectors.toList());
                Map<Boolean, List<StudentUploadDto>> studentUploadMap = processRows(rows, cashService.cashAllColleges(), cashService.cashAllDepartments());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        return new MessageResponse(200, "Students are being Registered", null);
    }

    private Map<Boolean, List<StudentUploadDto>> processRows(List<Row> rows, Map<Object, List<Object[]>> collegesMap, Map<Object, List<Object[]>> departmentsMap) {
        return rows.parallelStream().map(row -> {
            StudentUploadDto studentUploadDto = StudentUploadDto.builder()
                    .nameAr(row.getCell(0).getStringCellValue())
                    .nationality(row.getCell(1).getStringCellValue())
                    .nationalId(row.getCell(2).getStringCellValue())
                    .collegeCode(row.getCell(3).getStringCellValue())
                    .departmentCode(row.getCell(4).getStringCellValue())
                    .build();
            studentUploadDto.setErrors(validationService.validate(studentUploadDto).stream()
                    .map(violation -> new StringBuilder()
                            .append("Field ").append("-> ").append(violation.getPropertyPath()).append(System.lineSeparator())
                            .append("Error ").append("-> ").append(violation.getMessage()).append(System.lineSeparator()))
                    .collect(Collectors.joining()));
            validateCollegeAndDepartmentAndNationalID(collegesMap, departmentsMap, studentUploadDto);
            studentUploadDto.setIsValid(studentUploadDto.getErrors().equals(" "));
            return studentUploadDto;
        }).collect(Collectors.groupingBy(StudentUploadDto::getIsValid));
    }

    private void validateCollegeAndDepartmentAndNationalID(Map<Object, List<Object[]>> collegesMap, Map<Object, List<Object[]>> departmentsMap, StudentUploadDto studentUploadDto) {
        Optional<List<Object[]>> optionalListColleges = Optional.ofNullable(collegesMap.get(studentUploadDto.getCollegeCode()));
        if (optionalListColleges.isEmpty()) {
            studentUploadDto.setErrors(new StringBuilder(studentUploadDto.getErrors()).append("Field ").append("-> ").append(" college-code").append(System.lineSeparator())
                    .append("Error ").append("-> ").append("college doesnt exist").append(System.lineSeparator()).toString());
        } else {
            Optional<List<Object[]>> optionalListDepartments = Optional.ofNullable(departmentsMap.get(optionalListColleges.stream().findFirst()));
            if (optionalListDepartments.isEmpty() || optionalListDepartments.get().stream().noneMatch(objects -> objects[0] == studentUploadDto.getDepartmentCode())) {
                studentUploadDto.setErrors(new StringBuilder(studentUploadDto.getErrors()).append("Field ").append("-> ").append(" department-code").append(System.lineSeparator())
                        .append("Error ").append("-> ").append("department doesnt exist for the given college").append(System.lineSeparator()).toString());
            }
        }
        if (studentRepository.existsByNationalId(studentUploadDto.getNationalId())) {
            studentUploadDto.setErrors(new StringBuilder(studentUploadDto.getErrors()).append("Field ").append("-> ").append(" national-ID").append(System.lineSeparator())
                    .append("Error ").append("-> ").append("National ID already on the system").append(System.lineSeparator()).toString());
        }
    }


    public MessageResponse uploadProfilePicture(MultipartFile file, String email, Long userID) {
        FileUploadDownLoadModel fileUploadDownLoadModel = constructUploadModelForPictureUpload(email, file.getOriginalFilename());
        return uploadToDrive(file, fileUploadDownLoadModel, userID);
    }

    private MessageResponse uploadToDrive(MultipartFile file, FileUploadDownLoadModel fileUploadDownLoadModel, Long userID) {
        try {
            String fileUploadModel = objectMapper.writeValueAsString(fileUploadDownLoadModel);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.MULTIPART_FORM_DATA);
            MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
            body.add("file", Base64.getEncoder().encodeToString(file.getBytes()));
            body.add("fileUploadModel", fileUploadModel);
            HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);
            String content = restTemplate.exchange(uploadProfilePictureUrl, HttpMethod.POST, requestEntity, String.class).getBody();
            MessageResponse messageResponse = objectMapper.readValue(content, MessageResponse.class);
            userFileRepository.deleteAllByType(Constants.FILE_TYPE_PROFILE_PICTURE);
            fileUploadDownLoadModel.setFileName(messageResponse.getMessage());
            String directories = saveFile(fileUploadDownLoadModel, userID);
            messageResponse.setMessage(directories);
            return messageResponse;
        } catch (Exception e) {
            logger.error("error uploading file", e);
            return new MessageResponse(500, "error Uploading file", null);
        }
    }

    private String saveFile(FileUploadDownLoadModel fileUploadDownLoadModel, Long userID) {
        String directories = String.join(Constants.DASH_DELIMITER, fileUploadDownLoadModel.getDirectories()) +
                Constants.DASH_DELIMITER + fileUploadDownLoadModel.getFileName();
        userFileRepository.saveUserFile(directories,
                fileUploadDownLoadModel.getFileName(), userID, Constants.FILE_TYPE_PROFILE_PICTURE);
        return directories;
    }

    private FileUploadDownLoadModel constructUploadModelForPictureUpload(String email, String originalFilename) {
        List<String> directories = Arrays.asList(email, Constants.PROFILE_PICTURE_FOLDER_NAME);
        return FileUploadDownLoadModel.builder().directories(directories).removeOthers(true).fileName(originalFilename).build();
    }

}
