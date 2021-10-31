package com.sis.service;

import com.sis.dao.StudentRepository;
import com.sis.dto.StudentDTO;
import com.sis.entities.Student;
import com.sis.entities.mapper.StudentMapper;
import com.sis.util.PageQueryUtil;
import com.sis.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentService extends BaseServiceImp<Student>{

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public JpaRepository<Student, Long> Repository() {
        return this.studentRepository;
    }



    public PageResult<Student> filterStudents(PageQueryUtil pageUtil, long collegeId, long departmentId) {

        Pageable pageable = PageRequest.of(pageUtil.getPage() - 1, pageUtil.getLimit());
        Page<Student> page=null;
        if(departmentId==-1) {
            page = this.studentRepository.findStudentsByCollage(collegeId, pageable);
        }else{
            page = this.studentRepository.findStudentsByCollage(collegeId, departmentId, pageable);
        }

        PageResult<Student> pageResult = new PageResult<Student>(page.getContent(), (int) page.getTotalElements(),
                pageUtil.getLimit(), pageUtil.getPage());
        return pageResult;
    }
    public PageResult<StudentDTO> filterStudentsDTO(long collegeId, long departmentId ,int page, int limit ){

        PageQueryUtil pgq=new PageQueryUtil(page,limit);
        PageResult<Student> students=this.filterStudents(pgq,collegeId,departmentId);
        return this.studentMapper.toDataPage(students);
    }


    public PageResult<Student> searchStudents(PageQueryUtil pageUtil, Optional<String> attribute, Optional<Long>  collegeId, Optional<Long>  departmentId) {

        Pageable pageable = PageRequest.of(pageUtil.getPage() - 1, pageUtil.getLimit());
        Page<Student> page=null;
        long num=-1;
        if(attribute.isPresent()) {
            try {

                num = Long.parseLong(attribute.get());
                // is an integer
            } catch (NumberFormatException e) {
                // not an integer!
                num = -1;

            }
        }
        if(attribute.isPresent() && !collegeId.isPresent()){

            page = this.studentRepository.searchStudent(attribute.get(),num,pageable);
        }else if(attribute.isPresent() && collegeId.isPresent() && departmentId.isPresent()){
            page=this.studentRepository.searchStudent(attribute.get(), num,collegeId.get(), departmentId.get() ,pageable);
        }else if(attribute.isPresent() && collegeId.isPresent() && !departmentId.isPresent()){
            System.out.println("abdo");
            page=this.studentRepository.searchStudent(attribute.get(), num,collegeId.get() ,pageable);

        }else if(!attribute.isPresent() && collegeId.isPresent() && !departmentId.isPresent()){

            page=this.studentRepository.findStudentsByCollage(collegeId.get(),pageable);

        }else if(!attribute.isPresent() && collegeId.isPresent() && departmentId.isPresent()) {

            page=this.studentRepository.findStudentsByCollage(collegeId.get(),departmentId.get(),pageable);

        }else{
            System.out.println("im hereee");
            page=this.studentRepository.findAll(pageable);
        }

        PageResult<Student> pageResult = new PageResult<Student>(page.getContent(), (int) page.getTotalElements(),
                pageUtil.getLimit(), pageUtil.getPage());
        return pageResult;
    }

    public PageResult<StudentDTO> searchStudentsDTO(Optional<String> attribute, Optional<Long> collegeId, Optional<Long>  departmentId, int page, int limit ){

        PageQueryUtil pgq=new PageQueryUtil(page,limit);

        PageResult<Student> students=this.searchStudents(pgq,attribute, collegeId,  departmentId);
        return this.studentMapper.toDataPage(students);
    }




    public Student findByuniversityId(long universityId){

        return this.studentRepository.findByUniversityId(universityId);
    }

    public Student findByNationalId(String id){
        return this.studentRepository.findByNationalId(id);
    }
    public Student findByUniversityMail(String mail){
        return this.studentRepository.findByUniversityMail(mail);
    }


}


