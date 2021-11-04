package com.sis.controller;

import com.sis.dto.StudentDTO;
import com.sis.entities.Student;
import com.sis.entities.mapper.StudentMapper;
import com.sis.exception.StudentNotFoundException;
import com.sis.service.StudentService;
import com.sis.util.MessageResponse;
import com.sis.util.PageQueryUtil;
import com.sis.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "/api/students")
public class StudentController extends BaseController<Student, StudentDTO> {
    @Autowired
    private StudentService studentService;

    @Autowired
    private StudentMapper studentMapper;

    @RequestMapping(value="/addStudent", method = RequestMethod.POST)
    public MessageResponse create(@RequestBody StudentDTO dto) {

        if(this.studentService.findByuniversityId(dto.getUniversityId())!=null){

            throw new StudentNotFoundException("error at university id");
        }
        if(this.studentService.findByNationalId(dto.getNationalId())!=null){
            throw new StudentNotFoundException("error at national id");
        }
        if(this.studentService.findByUniversityMail(dto.getUniversityMail())!=null){
            throw new StudentNotFoundException("error at university mail ");
        }
        this.studentService.save(this.studentMapper.toEntity(dto));
        return new MessageResponse("Item has been saved successfully");
    }

    @RequestMapping(value = "/updateStudent", method = RequestMethod.PUT)
    public MessageResponse update( @RequestBody StudentDTO dto) {


        Student st = this.studentService.findById(dto.getId());
        Student studentByuniversityID=this.studentService.findByuniversityId(dto.getUniversityId());
        Student studentByNationalID=this.studentService.findByNationalId(dto.getNationalId());
        Student studentByuniversityMail=this.studentService.findByUniversityMail(dto.getUniversityMail());
        if(studentByuniversityID!=null && studentByuniversityID.getId() != dto.getId()){
            System.out.println("dto= "+dto.getId());
            System.out.println("entity = "+studentByuniversityID.getId());
            throw new StudentNotFoundException("error at university id");
        }
        if(studentByNationalID!=null && studentByNationalID.getId() != dto.getId()){
            throw new StudentNotFoundException("error at national id");
        }
        if(studentByuniversityMail!=null && studentByuniversityMail.getId() != dto.getId()){
            throw new StudentNotFoundException("error at university mail ");
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
            value = "/search/{attribute}",
            method = RequestMethod.GET
    )
    public ResponseEntity<PageResult<StudentDTO>> searchStudentPage(@PathVariable Optional<String> attribute, @RequestParam(required = false) Optional<Long> collageId, @RequestParam(required = false) Optional<Long>  departmentId, @RequestParam() int page, @RequestParam() int limit) {
        //this.studentService.getDataPage(attribute);
        System.out.println(attribute.isPresent());
        System.out.println(attribute.get());
        PageResult<StudentDTO> result=this.studentService.searchStudentsDTO(attribute,collageId, departmentId, page,limit);
        return new ResponseEntity<PageResult<StudentDTO>>(result, HttpStatus.OK);
    }
    @RequestMapping(
            value = "/search",
            method = RequestMethod.GET
    )
    public ResponseEntity<PageResult<StudentDTO>> filterStudentPage( @RequestParam(required = false) Optional<Long> collageId , @RequestParam(required = false) Optional<Long>  departmentId, @RequestParam() int page, @RequestParam() int limit) {
        //this.studentService.getDataPage(attribute);
        System.out.println("yess");

        Optional<String> opt=Optional.ofNullable(null);
        System.out.println(opt.isPresent());
        PageResult<StudentDTO> result=this.studentService.searchStudentsDTO(opt,collageId, departmentId, page,limit);
        return new ResponseEntity<PageResult<StudentDTO>>(result, HttpStatus.OK);

    }
    @RequestMapping(value="/sort", method = RequestMethod.POST)
    public PageResult<StudentDTO> getStudentPage(@RequestBody  PageQueryUtil pageUtil,@RequestParam() String sortField,@RequestParam() String direction) {
//        System.out.println(pageUtil.getPage());
        Sort.Direction d= Sort.Direction.ASC;
        if(direction.equals("asc")){

        }else{
            d= Sort.Direction.DESC;
        }
       return this.studentMapper.toDataPage(this.studentService.getDataPage(pageUtil,sortField, d));

    }



}
