package com.sis.service;

import com.sis.dao.StudentEnrollmentRepository;
import com.sis.dao.StudentRepository;
import com.sis.dto.student.StudentDTO;
import com.sis.dto.student.StudentFilterDTO;
import com.sis.entities.Student;
import com.sis.entities.StudentEnrollment;
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

import java.util.ArrayList;


@Service
public class StudentService extends BaseServiceImp<Student>{

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private StudentEnrollmentRepository studentEnrollmentRepository;

    @Override
    public JpaRepository<Student, Long> Repository() {
        return this.studentRepository;
    }




    public PageResult<Student> searchStudents(PageQueryUtil pageUtil, String attribute,long  collegeId,
                                              long  departmentId,String level, @Nullable String sortField,@Nullable Sort.Direction sort) {
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

        if(attribute!=null && collegeId==-1 && level ==null){

            System.out.println("hhjh");
            page = this.studentRepository.searchStudent(attribute,num,pageable);
        }else if(attribute!=null && collegeId==-1 && level !=null){
            page=this.studentRepository.searchStudentByLevel(attribute, num,level,pageable);
        }else if(attribute!=null && collegeId!=-1 && level !=null && departmentId==-1){
            page=this.studentRepository.searchStudentByLevel(attribute, num,collegeId,level,pageable);
        }
        else if(attribute!=null && collegeId!=-1 && departmentId!=-1 && level ==null){

            page=this.studentRepository.searchStudent(attribute, num,collegeId, departmentId,pageable);
        }else if(attribute!=null && collegeId!=-1 && departmentId==-1 && level ==null){

            page=this.studentRepository.searchStudent(attribute, num,collegeId ,pageable);

        }else if(attribute!=null && collegeId!=-1 && departmentId!=-1 && level !=null){
            page=this.studentRepository.searchStudent(attribute, num,collegeId,departmentId,level,pageable);

        }
        else if(attribute==null && collegeId!=-1 && departmentId==-1 && level ==null){

//            s=Sort.by("name_ar").ascending();
            Pageable pageable2 = PageRequest.of(pageUtil.getPage() - 1, pageUtil.getLimit(),s);
            page=this.studentRepository.findStudentsByCollage(collegeId,pageable2);

        }else if(attribute==null && collegeId!=-1 && departmentId!=-1&& level ==null) {

            page=this.studentRepository.findStudentsByCollage(collegeId,departmentId,pageable);

        }else if(attribute==null && collegeId==-1 && departmentId==-1&& level !=null) {

            Pageable pageable2 = PageRequest.of(pageUtil.getPage() - 1, pageUtil.getLimit(),s);
            page=this.studentRepository.findStudentByLevel(level,pageable2);

        }
        else if(attribute==null && collegeId!=-1 && departmentId==-1&& level !=null) {

            Pageable pageable2 = PageRequest.of(pageUtil.getPage() - 1, pageUtil.getLimit(),s);
            page=this.studentRepository.CollegeIdLevel(collegeId,level,pageable2);

        }
        else if(attribute==null && collegeId!=-1 && departmentId!=-1&& level !=null) {

            Pageable pageable2 = PageRequest.of(pageUtil.getPage() - 1, pageUtil.getLimit(),s);
            page=this.studentRepository.findByCollegeIdAndDepartmentIdAndLevel(collegeId,departmentId,level,pageable2);

        }
        else{

//            page=this.studentRepository.findAll(pageable);
            page=this.studentRepository.findAllStudent(pageable);
        }

        return new PageResult<Student>(page.getContent(), (int) page.getTotalElements(),
                pageUtil.getLimit(), pageUtil.getPage());
    }

    public PageResult<StudentDTO> searchStudentsDTO(String attribute, long collegeId, long  departmentId,String level,
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
        PageResult<Student> students=this.searchStudents(pgq,attribute, collegeId,  departmentId,level,studentFilterDTO.getSortBy(),direction);
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

    public ArrayList<StudentDTO> findStudentsBySection(long academicYearId, long academicTermId, long sectionId){
        ArrayList<StudentEnrollment> studentEnrollments= this.studentEnrollmentRepository.findStudentsBySection(academicYearId,academicTermId,sectionId);
        ArrayList<Student> students=new ArrayList<>();
        if(studentEnrollments!=null) {
            for(StudentEnrollment st:studentEnrollments) {
                students.add(st.getStudent());
            }
            return this.studentMapper.toDTOs(students);
        }
        return null;
    }


}


