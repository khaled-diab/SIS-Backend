package com.sis.service;

import com.sis.dto.AdminDto;
import com.sis.dto.BaseDTO;
import com.sis.dto.DepartmentProjection;
import com.sis.dto.UserFileDto;
import com.sis.dto.college.CollegeProjection;
import com.sis.dto.college.GeneralSearchRequest;
import com.sis.dto.security.LoginDTO;
import com.sis.dto.security.ProfilePassword;
import com.sis.dto.security.RegisterDTO;
import com.sis.dto.security.UserUploadDto;
import com.sis.entity.Degree;
import com.sis.entity.FacultyMember;
import com.sis.entity.Student;
import com.sis.entity.UserFile;
import com.sis.entity.mapper.FacultyMemberMapper;
import com.sis.entity.mapper.StudentMapper;
import com.sis.entity.mapper.UserFileMapper;
import com.sis.entity.mapper.UserMapper;
import com.sis.entity.security.User;
import com.sis.exception.InvalidUserNameOrPasswordException;
import com.sis.repository.*;
import com.sis.security.JwtProvider;
import com.sis.util.Constants;
import com.sis.util.MessageResponse;
import com.sis.util.PageResult;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static com.sis.util.Constants.FILE_TYPE_STAFF_UPLOAD;
import static com.sis.util.Constants.FILE_TYPE_STUDENT_UPLOAD;

@Service
@RequiredArgsConstructor
public class SecurityService extends BaseServiceImp<User> {

    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());
    private final UserRepository userRepository;
    private final UserFileRepository userFileRepository;
    private final UserFileMapper userFileMapper;
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
    private final DegreeRepository degreeRepository;
    private final PojoValidationService validationService;
    private final ExcelFileGenerator excelFileGenerator;
    private final UploadFilesService uploadFilesService;
    private final WebSocketService webSocketService;


    public MessageResponse registerStudent(RegisterDTO registerDTO) {
        CompletableFuture<Boolean> nationalIDExist = CompletableFuture.supplyAsync(() -> studentRepository.existsByNationalId(registerDTO.getNationalityID()));
        CompletableFuture<Boolean> mailExist = CompletableFuture.supplyAsync(() -> studentRepository.existsByUniversityMail(registerDTO.getEmail()));
        CompletableFuture<Boolean> phoneExist = CompletableFuture.supplyAsync(() -> studentRepository.existsByPhone(registerDTO.getPhoneNumber()));
        CompletableFuture.allOf(nationalIDExist, mailExist, phoneExist);
        if (!Boolean.TRUE.equals(nationalIDExist.join())) {
            return new MessageResponse(500, Constants.NATIONAL_ID_ERROR, null);
        } else if (Boolean.TRUE.equals(phoneExist.join())) {
            return new MessageResponse(500, "Phone Number Already Exist", null);
        } else {
            Student student = studentRepository.findByNationalId(registerDTO.getNationalityID());
            if (Boolean.TRUE.equals(mailExist.join()) || student.getUniversityMail() != null) {
                return new MessageResponse(500, "Already registered go to login", null);
            } else if (Boolean.TRUE.equals(!mailExist.join())) {
                return registerStudent(registerDTO, student);
            } else {
                return new MessageResponse(500, Constants.NATIONAL_ID_ERROR, null);
            }
        }
    }

    public MessageResponse registerFacultyMember(RegisterDTO registerDTO) {
        CompletableFuture<Boolean> nationalIDExist = CompletableFuture.supplyAsync(() -> facultyMemberRepository.existsByNationalID(registerDTO.getNationalityID()));
        CompletableFuture<Boolean> mailExist = CompletableFuture.supplyAsync(() -> facultyMemberRepository.existsByUniversityMail(registerDTO.getEmail()));
        CompletableFuture<Boolean> phoneExist = CompletableFuture.supplyAsync(() -> facultyMemberRepository.existsByPhone(registerDTO.getPhoneNumber()));
        CompletableFuture.allOf(nationalIDExist, mailExist, phoneExist);
        if (!Boolean.TRUE.equals(nationalIDExist.join())) {
            return new MessageResponse(500, Constants.NATIONAL_ID_ERROR, null);
        } else if (Boolean.TRUE.equals(phoneExist.join())) {
            return new MessageResponse(500, "Phone Number Already Exist", null);
        } else {
            FacultyMember facultyMember = facultyMemberRepository.findByNationalID(registerDTO.getNationalityID());
            if (Boolean.TRUE.equals(mailExist.join()) || facultyMember.getUniversityMail() != null) {
                return new MessageResponse(500, "Already registered go to login", null);
            } else if (Boolean.TRUE.equals(!mailExist.join())) {
                return registerFacultyMember(registerDTO, facultyMember);
            } else {
                return new MessageResponse(500, Constants.NATIONAL_ID_ERROR, null);
            }
        }
    }

    private MessageResponse registerFacultyMember(RegisterDTO registerDTO, FacultyMember facultyMember) {
        User user = User.builder()
                .email(registerDTO.getEmail())
                .username(registerDTO.getEmail())
                .password(passwordEncoder.encode(registerDTO.getPassword()))
                .role(roleRepository.getRoleFacultyMember())
                .firstname(facultyMember.getNameEn())
                .type(Constants.TYPE_STAFF).build();
        facultyMember.setBirthDate(registerDTO.getBirthDate());
        facultyMember.setUser(userRepository.save(user));
        facultyMember.setUniversityMail(registerDTO.getEmail());
        facultyMember.setPhone(registerDTO.getPhoneNumber());
        facultyMemberRepository.save(facultyMember);
        return MessageResponse.builder().message("Staff Member registered Successfully").build();
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
        student.setPhone(registerDTO.getPhoneNumber());
        studentRepository.save(student);
        return MessageResponse.builder().message("Student registered Successfully").build();
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
        } else if (user.getType().equals(Constants.TYPE_STAFF)) {
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

    public MessageResponse registerBulkUsers(MultipartFile file, String userType) {
        CompletableFuture.runAsync(() -> {
            try {
                try (XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream())) {
                    Sheet sheet = workbook.getSheetAt(0);
                    sheet.removeRow(sheet.iterator().next());
                    List<Row> rows = StreamSupport.stream(sheet.spliterator(), true).collect(Collectors.toList());
                    Map<String, List<CollegeProjection>> collegesMap = cashService.cashAllColleges();
                    Map<Long, List<DepartmentProjection>> departmentsMap = cashService.cashAllDepartments();
                    Map<Long, List<Degree>> degreeMap = cashService.degreeMap();
                    Map<Boolean, List<UserUploadDto>> usersUploadMap = processRows(rows, collegesMap, departmentsMap, degreeMap, userType);
                    saveValidUsers(usersUploadMap.get(Boolean.TRUE), userType);
                    uploadInvalidUserToDrive(usersUploadMap.get(Boolean.FALSE), file.getOriginalFilename(), userType);
                    webSocketService.sendUploadDoneNotification(200, "File processing is done");
                }
            } catch (Exception e) {
                logger.error("error opining file", e);
                webSocketService.sendUploadDoneNotification(500, "File processing has Filed try Renaming File or try again Later");
            }
        });
        return new MessageResponse(200, "Students are being Registered", null);
    }

    private Map<Boolean, List<UserUploadDto>> processRows(List<Row> rows, Map<String, List<CollegeProjection>> collegesMap, Map<Long, List<DepartmentProjection>> departmentsMap, Map<Long, List<Degree>> degreeMap, String userType) {
        return rows.stream().map(row -> {
            UserUploadDto userUploadDto = UserUploadDto.builder()
                    .nameAr(row.getCell(0).getStringCellValue())
                    .nationality(row.getCell(1).getStringCellValue())
                    .nationalId(row.getCell(2).getStringCellValue())
                    .collegeCode(row.getCell(3).getStringCellValue())
                    .departmentCode(row.getCell(4).getStringCellValue())
                    .build();
            if (userType.equals(Constants.TYPE_STUDENT)) {
                userUploadDto.setUniversityNumber(row.getCell(5).getStringCellValue());
            } else {
                userUploadDto.setDegreeID(row.getCell(5).getStringCellValue());
            }
            userUploadDto.setErrors(validationService.validate(userUploadDto).stream()
                    .map(violation -> new StringBuilder()
                            .append(Constants.FIELD).append(" -> ").append(violation.getPropertyPath()).append(System.lineSeparator())
                            .append(" ")
                            .append(Constants.ERROR).append(" -> ").append(violation.getMessage()).append(System.lineSeparator()))
                    .collect(Collectors.joining()));
            dbValidation(collegesMap, departmentsMap, degreeMap, userUploadDto, userType);
            userUploadDto.setIsValid(userUploadDto.getErrors().isEmpty());
            return userUploadDto;
        }).collect(Collectors.groupingBy(UserUploadDto::getIsValid));
    }

    private void dbValidation(Map<String, List<CollegeProjection>> collegesMap, Map<Long, List<DepartmentProjection>> departmentsMap, Map<Long, List<Degree>> degreeMap, UserUploadDto userUploadDto, String userType) {
        Optional<List<CollegeProjection>> optionalListColleges = Optional.ofNullable(collegesMap.get(userUploadDto.getCollegeCode()));
        if (optionalListColleges.isEmpty()) {
            userUploadDto.setErrors(userUploadDto.getErrors() + Constants.FIELD + " -> " + " college-code " + System.lineSeparator() +
                    Constants.ERROR + "-> " + "college doesnt exist" + System.lineSeparator());
        } else {
            Optional<List<DepartmentProjection>> optionalListDepartments = Optional.ofNullable(departmentsMap.get(optionalListColleges.get().get(0).getId()));
            boolean noneMatch = optionalListDepartments.get().stream().noneMatch(departmentProjection -> Objects.equals(departmentProjection.getCode(), userUploadDto.getDepartmentCode()));
            if (noneMatch) {
                userUploadDto.setErrors(userUploadDto.getErrors() + Constants.FIELD + " -> " + " department-code " + System.lineSeparator() +
                        Constants.ERROR + " -> " + " department doesnt exist for the given college" + System.lineSeparator());
            }
        }
        if (userType.equals(Constants.TYPE_STUDENT)) {
            if (Boolean.TRUE.equals(studentRepository.existsByNationalId(userUploadDto.getNationalId()))) {
                userUploadDto.setErrors(userUploadDto.getErrors() + Constants.FIELD + " -> " + " national-ID " + System.lineSeparator() +
                        Constants.ERROR + " -> " + " National ID already on the system" + System.lineSeparator());
            }
            if (Boolean.TRUE.equals(studentRepository.existsByUniversityId(Long.valueOf(userUploadDto.getUniversityNumber())))) {
                userUploadDto.setErrors(userUploadDto.getErrors() + Constants.FIELD + " -> " + " university-number " + System.lineSeparator() +
                        Constants.ERROR + " -> " + " university number already on the system" + System.lineSeparator());
            }
        } else {
            if (Boolean.TRUE.equals(facultyMemberRepository.existsByNationalID(userUploadDto.getNationalId()))) {
                userUploadDto.setErrors(userUploadDto.getErrors() + Constants.FIELD + " -> " + " national-ID " + System.lineSeparator() +
                        Constants.ERROR + " -> " + " National ID already on the system" + System.lineSeparator());
            }
            if (userUploadDto.getDegreeID() == null || Optional.ofNullable(degreeMap.get(Long.valueOf(userUploadDto.getDegreeID()))).isEmpty()) {
                userUploadDto.setErrors(userUploadDto.getErrors() + Constants.FIELD + " -> " + " degree-ID " + System.lineSeparator() +
                        Constants.ERROR + " -> " + " degree does not exist" + System.lineSeparator());
            }
        }
    }

    private void uploadInvalidUserToDrive(List<UserUploadDto> userUploadDtoList, String fileName, String userType) throws IOException {
        String excelSheetEncoded = excelFileGenerator.generateInvalidUsersExcelSheet(userUploadDtoList, userType);
        uploadInvalidUsers(excelSheetEncoded, fileName, userType);
    }

    private void saveValidUsers(List<UserUploadDto> userUploadDtoList, String userType) {
        if (userUploadDtoList != null) {
            if (Objects.equals(userType, Constants.TYPE_STUDENT)) {
                studentRepository.saveAll(userUploadDtoList
                        .parallelStream()
                        .map(userUploadDto -> Student.builder()
                                .nationalId(userUploadDto.getNationalId())
                                .nationality(userUploadDto.getNationality())
                                .collegeId(collegeRepository.findByCode(userUploadDto.getCollegeCode()))
                                .departmentId(departmentRepository.findByCode(userUploadDto.getDepartmentCode()))
                                .nameAr(userUploadDto.getNameAr())
                                .level("1")
                                .universityId(Long.parseLong(userUploadDto.getUniversityNumber()))
                                .build())
                        .collect(Collectors.toList()));
            } else {
                facultyMemberRepository.saveAll(userUploadDtoList.parallelStream()
                        .map(userUploadDto -> FacultyMember.builder()
                                .nationalID(userUploadDto.getNationalId())
                                .nationality(userUploadDto.getNationality())
                                .college(collegeRepository.findByCode(userUploadDto.getCollegeCode()))
                                .department(departmentRepository.findByCode(userUploadDto.getDepartmentCode()))
                                .nameAr(userUploadDto.getNameAr())
                                .degree(degreeRepository.findDegreeById(Long.valueOf(userUploadDto.getDegreeID())))
                                .build())
                        .collect(Collectors.toList()));
            }
        }
    }


    public void uploadInvalidUsers(String file, String fileName, String userType) {
        uploadFilesService.uploadUsers(file, fileName, Constants.ADMIN_USER_NAME, userType);
    }

    public MessageResponse uploadProfilePicture(MultipartFile file, String email) throws IOException {
        return uploadFilesService.uploadProfilePicture(Base64.getEncoder().encodeToString(file.getBytes()), file.getOriginalFilename(), email);
    }


    public PageResult<UserFileDto> getAdminUploadedFiles(Integer page, Integer size, GeneralSearchRequest generalSearchRequest) {
        Page<UserFile> userFilePage;
        PageRequest pageRequest = PageRequest.of(page, size, constructSortObject(generalSearchRequest, "type"));
        if (generalSearchRequest.getFilterValue() != null && !generalSearchRequest.getFilterValue().equals("")) {
            userFilePage = this.userFileRepository.findAllByFileNameContainingIgnoreCaseAndTypeIn(
                    generalSearchRequest.getFilterValue(), Arrays.asList(FILE_TYPE_STAFF_UPLOAD, FILE_TYPE_STUDENT_UPLOAD), pageRequest);
        } else {
            userFilePage = this.userFileRepository.findAllByTypeIn(Arrays.asList(FILE_TYPE_STAFF_UPLOAD, FILE_TYPE_STUDENT_UPLOAD), pageRequest);
        }
        return userFileMapper.toDataPage(new PageResult<>(userFilePage.getContent(),
                (int) userFilePage.getTotalElements(),
                userFilePage.getSize(),
                userFilePage.getNumber()));
    }
    public Boolean changePassword( ProfilePassword profilePassword) {
        Optional<User> user = this.userRepository.findByUsername(profilePassword.getUserName());
        if(user.isPresent()){
            String oldPass = user.get().getPassword();
            if(this.passwordEncoder.matches(profilePassword.getOldPass(),oldPass)){
                user.get().setPassword(passwordEncoder.encode(profilePassword.getNewPass()));
                this.userRepository.save(user.get());
                return  true;
            }else{
                throw new InvalidUserNameOrPasswordException("Invalid password", "oldPass");
            }
        }
        return false;
    }

    @Override
    public JpaRepository<User, Long> Repository() {
        return null;
    }
}
