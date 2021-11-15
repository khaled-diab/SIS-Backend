package com.sis.controller;

import com.sis.dto.StudentDTO;
import com.sis.dto.StudentFilterDTO;
import com.sis.entities.Student;
import com.sis.entities.mapper.StudentMapper;
import com.sis.exception.StudentFieldNotUniqueException;
import com.sis.service.StudentService;
import com.sis.util.MessageResponse;
import com.sis.util.PageResult;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;


@RestController
@Validated
@RequestMapping(value = "/api/students")
@AllArgsConstructor
public class StudentController extends BaseController<Student, StudentDTO> {

    //Autowired
    private final StudentService studentService;
    //Autowired
    private final StudentMapper studentMapper;

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
            value = "/search",
            method = RequestMethod.POST
    )
    public ResponseEntity<PageResult<StudentDTO>> searchStudentPage(
                                                                    @RequestParam int page, @RequestParam int limit,
                                                                    @RequestBody StudentFilterDTO filterDTO ) {
        //this.studentService.getDataPage(attribute);
        System.out.println("abdo");
        PageResult<StudentDTO> result=this.studentService.searchStudentsDTO(filterDTO.getFilterValue(),filterDTO.getCollegeId(), filterDTO.getDepartmentId(), page,limit,filterDTO);
        return new ResponseEntity<PageResult<StudentDTO>>(result, HttpStatus.OK);
    }
//    @RequestMapping(
//            value = "/search",
//            method = RequestMethod.POST
//    )
//    public ResponseEntity<PageResult<StudentDTO>> filterStudentPage(@RequestParam int page, @RequestParam int limit,
//                                                                     @RequestBody StudentFilterDTO filterDTO) {
//
//        System.out.println("no attribute");
//        PageResult<StudentDTO> result=this.studentService.searchStudentsDTO(null,filterDTO.getCollegeId(),filterDTO.getDepartmentId(), page,limit,filterDTO);
//        return new ResponseEntity<PageResult<StudentDTO>>(result, HttpStatus.OK);
//
//    }



}
