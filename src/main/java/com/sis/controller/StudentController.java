package com.sis.controller;

import com.sis.dto.AcademicTermDTO;
import com.sis.dto.section.SectionDTO;
import com.sis.dto.student.StudentDTO;
import com.sis.dto.student.StudentFilterDTO;
import com.sis.entity.AcademicTerm;
import com.sis.entity.Student;
import com.sis.entity.mapper.AcademicTermMapper;
import com.sis.entity.mapper.StudentMapper;
import com.sis.exception.StudentFieldNotUniqueException;
import com.sis.service.AcademicTermService;
import com.sis.service.StudentService;
import com.sis.util.MessageResponse;
import com.sis.util.PageResult;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
import java.util.List;
import java.util.Objects;

import static java.nio.file.Files.copy;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import static org.springframework.http.HttpHeaders.CONTENT_DISPOSITION;

@RestController
@Validated
@RequestMapping(value = "/api/students")
@AllArgsConstructor
@CrossOrigin(origins = ("*"))

public class StudentController extends BaseController<Student, StudentDTO> {

    //Autowired
    private final StudentService studentService;
    //Autowired
    private final StudentMapper studentMapper;


    @Autowired
    private AcademicTermService academicTermService;

    @Autowired
    private AcademicTermMapper academicTermMapper;

    public static final String DIRECTORY =
            System.getProperty("user.home") + "/Resourcess/StudentImages/";
    @PostMapping("/upload")
    public ResponseEntity<List<String>> uploadFiles(@RequestParam("files")List<MultipartFile> multipartFiles) throws IOException {
        List<String> filenames = new ArrayList<>();
        for(MultipartFile file : multipartFiles) {
            String filename = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
            Path fileStorage = Paths.get(DIRECTORY, filename).toAbsolutePath().normalize();
            copy(file.getInputStream(), fileStorage, REPLACE_EXISTING);
            filenames.add(filename);
        }
        return ResponseEntity.ok().body(filenames);
    }
    @GetMapping("download/{filename}")
    public ResponseEntity<Resource> downloadFiles(@PathVariable("filename") String filename) throws IOException {
        Path filePath = Paths.get(DIRECTORY).toAbsolutePath().normalize().resolve(filename);
        if(!Files.exists(filePath)) {
            throw new FileNotFoundException(filename + " was not found on the server");
        }
        Resource resource = new UrlResource(filePath.toUri());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("File-Name", filename);
        httpHeaders.add(CONTENT_DISPOSITION, "attachment;File-Name=" + resource.getFilename());
        return ResponseEntity.ok().contentType(MediaType.parseMediaType(Files.probeContentType(filePath)))
                .headers(httpHeaders).body(resource);
    }
    @RequestMapping(value="/addStudent", method = RequestMethod.POST)
    public MessageResponse createStudent(@Valid @RequestBody  StudentDTO dto) {


        if(this.studentService.findByuniversityId(dto.getUniversityId())!=null){

            throw new StudentFieldNotUniqueException("universityId","University Id Already Exists");
        }
        if(this.studentService.findByNationalId(dto.getNationalId())!=null){
            throw new StudentFieldNotUniqueException("nationalId","NationalID Id Already Exists");
        }
        if(this.studentService.findByUniversityMail(dto.getUniversityMail())!=null){
            throw new StudentFieldNotUniqueException("universityMail","University Mail Already Exists");
        }
        this.studentService.save(this.studentMapper.toEntity(dto));
        return new MessageResponse("Item has been saved successfully");
    }

    @RequestMapping(value = "/updateStudent", method = RequestMethod.PUT)
    public MessageResponse updateStudent( @Valid @RequestBody StudentDTO dto) {

        Student st = this.studentService.findById(dto.getId());

        Student studentByuniversityID=this.studentService.findByuniversityId(dto.getUniversityId());
        Student studentByNationalID=this.studentService.findByNationalId(dto.getNationalId());
        Student studentByuniversityMail=this.studentService.findByUniversityMail(dto.getUniversityMail());
        if(studentByuniversityID!=null && studentByuniversityID.getId() != dto.getId()){
            System.out.println("dto= "+dto.getId());
            System.out.println("entity = "+studentByuniversityID.getId());
            throw new StudentFieldNotUniqueException("universityId","University Id Already Exists");
        }
        if(studentByNationalID!=null && studentByNationalID.getId() != dto.getId()){
            throw new StudentFieldNotUniqueException("nationalId","NationalID Id Already Exists");
        }
        if(studentByuniversityMail!=null && studentByuniversityMail.getId() != dto.getId()){
            throw new StudentFieldNotUniqueException("universityMail","University Mail Already Exists");
        }
        this.studentService.save(this.studentMapper.toEntity(dto));
        return new MessageResponse("Item has been updated successfully");
    }

    @RequestMapping(value = "/deleteStudent/{id}", method = RequestMethod.DELETE)
    public MessageResponse delete(@PathVariable(value = "id") Long id) {

        Student st=this.studentService.findById(id);

        this.studentService.deleteById(id);

        return new MessageResponse("Item has been deleted successfully");
    }

    @RequestMapping(
            value = "/searchStudent",
            method = RequestMethod.POST
    )
    public ResponseEntity<PageResult<StudentDTO>> searchStudentPage(
                                                                    @RequestParam int page, @RequestParam int limit,
                                                                    @RequestBody StudentFilterDTO filterDTO ) {
        PageResult<StudentDTO> result=this.studentService.searchStudentsDTO(filterDTO.getFilterValue(),filterDTO.getCollegeId(), filterDTO.getDepartmentId(), page,limit,filterDTO);
        return new ResponseEntity<PageResult<StudentDTO>>(result, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/studentsBySection",
            method = RequestMethod.POST
    )
    public ArrayList<StudentDTO> getStudentsBySection(@RequestBody ArrayList<SectionDTO> sectionDTOs) {
        AcademicTerm academicTerm = this.academicTermService.getCurrentAcademicTerm();
        AcademicTermDTO academicTermDTO = this.academicTermMapper.toDTO(academicTerm);
        ArrayList<StudentDTO> studentDTOs=new ArrayList<>();
       for(SectionDTO sectionDTO :sectionDTOs){
           studentDTOs.addAll(this.studentService.findStudentsBySection(academicTermDTO.getAcademicYearDTO().getId(),academicTermDTO.getId(),sectionDTO.getId()));
       }
       return studentDTOs;

    }


}
