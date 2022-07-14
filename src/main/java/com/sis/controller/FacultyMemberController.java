package com.sis.controller;

import com.sis.dto.facultyMember.FacultyMemberDTO;
import com.sis.dto.facultyMember.FacultyMemberRequestDTO;
import com.sis.dto.facultyMember.FacultyMemberTableRecordsDTO;
import com.sis.dto.timetable.TimetableDTO;
import com.sis.entity.FacultyMember;
import com.sis.entity.Student;
import com.sis.entity.mapper.FacultyMemberMapper;
import com.sis.entity.security.User;
import com.sis.exception.FacultyMemberFieldNotUniqueException;
import com.sis.repository.FacultyMemberRepository;
import com.sis.service.FacultyMemberService;
import com.sis.service.UserService;
import com.sis.util.MessageResponse;
import com.sis.util.PageQueryUtil;
import com.sis.util.PageResult;
import lombok.AllArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import static java.nio.file.Files.copy;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

@RestController
@Validated
@RequestMapping(value = "/api/facultyMembers")
@AllArgsConstructor
public class FacultyMemberController extends BaseController<FacultyMember, FacultyMemberDTO> {


    private final FacultyMemberService facultyMemberService;

    private final FacultyMemberMapper facultyMemberMapper;

    private final FacultyMemberRepository facultyMemberRepository;

    private final UserService userService;

    public static final String DIRECTORY = System.getProperty("user.dir") + "/src/main/resources/Images/facultyMemberImages/";

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public ResponseEntity<List<String>> uploadFiles(@RequestParam("files") List<MultipartFile> multipartFiles) throws IOException {
        List<String> filenames = new ArrayList<>();
        for (MultipartFile file : multipartFiles) {
            String filename = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
            Path fileStorage = Paths.get(DIRECTORY, filename).toAbsolutePath().normalize();
            copy(file.getInputStream(), fileStorage, REPLACE_EXISTING);
            filenames.add(filename);
        }
        return ResponseEntity.ok().body(filenames);
    }

    @RequestMapping(value = "/download/{filename}", method = RequestMethod.GET)
    public ResponseEntity<Resource> downloadFiles(@PathVariable("filename") String filename) throws IOException {
        Path filePath = Paths.get(DIRECTORY).toAbsolutePath().normalize().resolve(filename);
        if (!Files.exists(filePath)) {
            throw new FileNotFoundException(filename + " was not found on the server");
        }
        Resource resource = new UrlResource(filePath.toUri());
        return new ResponseEntity<>(resource, HttpStatus.OK);
    }

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

    @RequestMapping(value = "/facultyMemberByUserId/{userId}", method = RequestMethod.GET)
    public ResponseEntity<FacultyMemberDTO> facultyMemberByUserId(@PathVariable long userId) {
        User user = this.userService.findById(userId);
        FacultyMemberDTO facultyMemberDTO = this.facultyMemberMapper.toDTO(this.facultyMemberRepository.findFacultyMemberByUserId(user.getId()));
        return new ResponseEntity<>(facultyMemberDTO, HttpStatus.OK);
    }
}
