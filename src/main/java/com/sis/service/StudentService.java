package com.sis.service;

import com.sis.dao.StudentRepository;
import com.sis.dto.student.StudentDTO;
import com.sis.dto.student.StudentFilterDTO;
import com.sis.entities.Student;
import com.sis.entities.mapper.StudentMapper;
import com.sis.util.PageQueryUtil;
import com.sis.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;



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




    public PageResult<Student> searchStudents(PageQueryUtil pageUtil, String attribute,long  collegeId,
                                              long  departmentId, @Nullable String sortField,@Nullable Sort.Direction sort) {
        if(attribute!=null && attribute.equals("")){
            attribute=null;
        }
        Sort s=null;
        if(sort==null){
            s=Sort.by("name_ar").ascending();
        }
        else if(sort.equals(Sort.Direction.ASC)){
            s=Sort.by(sortField).ascending();
        }else {
            s=Sort.by(sortField).descending();
        }
//        Sort s = sort.equals(Sort.Direction.ASC) ? Sort.by(sortField).ascending()
//                : Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(pageUtil.getPage() - 1, pageUtil.getLimit(),s);
        Page<Student> page=null;
        long num=-1;

            try {

                num = Long.parseLong(attribute);
                // is an integer
            } catch (NumberFormatException e) {
                // not an integer!
                num = -1;
            }

        if(attribute!=null && collegeId==-1){
            System.out.println(11);
            page = this.studentRepository.searchStudent(attribute,num,pageable);
        }else if(attribute!=null && collegeId!=-1 && departmentId!=-1){
            System.out.println(22);
            page=this.studentRepository.searchStudent(attribute, num,collegeId, departmentId,pageable);
        }else if(attribute!=null && collegeId!=-1 && departmentId==-1){
            System.out.println(33);
            System.out.println("abdo");
            page=this.studentRepository.searchStudent(attribute, num,collegeId ,pageable);

        }else if(attribute==null && collegeId!=-1 && departmentId==-1){
            System.out.println(44);
//            s=Sort.by("name_ar").ascending();
            Pageable pageable2 = PageRequest.of(pageUtil.getPage() - 1, pageUtil.getLimit(),s);
            page=this.studentRepository.findStudentsByCollage(collegeId,pageable2);

        }else if(attribute==null && collegeId!=-1 && departmentId!=-1) {
            System.out.println(55);
            page=this.studentRepository.findStudentsByCollage(collegeId,departmentId,pageable);

        }else{
            System.out.println(66);
            System.out.println("im hereee");
//            page=this.studentRepository.findAll(pageable);
            page=this.studentRepository.findAllStudent(pageable);
        }

        PageResult<Student> pageResult = new PageResult<Student>(page.getContent(), (int) page.getTotalElements(),
                pageUtil.getLimit(), pageUtil.getPage());
        return pageResult;
    }

    public PageResult<StudentDTO> searchStudentsDTO(String attribute, long collegeId, long  departmentId,
                                                    int page, int limit, StudentFilterDTO studentFilterDTO ){
        Sort.Direction direction=null;

        if(studentFilterDTO.getSortDirection()==null){

        }
        else if(studentFilterDTO.getSortDirection().equals("ASC")){
            direction= Sort.Direction.ASC;
        }else {
            direction= Sort.Direction.DESC;
        }
        PageQueryUtil pgq=new PageQueryUtil(page,limit);
        PageResult<Student> students=this.searchStudents(pgq,attribute, collegeId,  departmentId,studentFilterDTO.getSortBy(),direction);
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


