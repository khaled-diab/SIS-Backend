package com.sis.service;

import com.sis.dto.student.StudentDTO;
import com.sis.dto.student.StudentFilterDTO;
import com.sis.entity.Student;
import com.sis.entity.StudentEnrollment;
import com.sis.entity.mapper.StudentMapper;
import com.sis.repository.StudentEnrollmentRepository;
import com.sis.repository.StudentRepository;
import com.sis.repository.specification.StudentSpecification;
import com.sis.util.PageQueryUtil;
import com.sis.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


@Service
public class StudentService extends BaseServiceImp<Student> {

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

    public Student findByuniversityId(long universityId) {

        return this.studentRepository.findByUniversityId(universityId);
    }

    public Student findByNationalId(String id) {
        return this.studentRepository.findByNationalIdNative(id);
    }

    public Student findByUniversityMail(String mail) {
        return this.studentRepository.findByUniversityMail(mail);
    }

    public ArrayList<StudentDTO> findStudentsBySection(long academicYearId, long academicTermId, long sectionId) {
        ArrayList<StudentEnrollment> studentEnrollments = this.studentEnrollmentRepository.findStudentsBySection(academicYearId, academicTermId, sectionId);
        ArrayList<Student> students = new ArrayList<>();
        if (studentEnrollments != null) {
            for (StudentEnrollment st : studentEnrollments) {
                students.add(st.getStudent());
            }
            return this.studentMapper.toDTOs(students);
        }
        return null;
    }

    public PageResult<StudentDTO> search(PageQueryUtil pageUtil, StudentFilterDTO studentFilterDTO) {
        Page<Student> studentPage;

        String searchValue = studentFilterDTO.getFilterValue();
        Long filterCollege = studentFilterDTO.getCollegeId();
        Long filterDepartment = studentFilterDTO.getDepartmentId();
        String filterLevel = studentFilterDTO.getLevel();
//        Long filterUser = studentFilterDTO.getUserId();

        Sort sort = constructSortObject(studentFilterDTO);
        Pageable pageable = PageRequest.of(pageUtil.getPage() - 1, pageUtil.getLimit(),sort);
        if ((searchValue != null && !searchValue.trim().isEmpty()) || filterCollege != null ||
                filterDepartment != null || (filterLevel != null && !filterLevel.trim().isEmpty())) {
            StudentSpecification studentSpecification = new StudentSpecification(searchValue, filterCollege, filterDepartment, filterLevel);
            studentPage=studentRepository.findAll(studentSpecification,pageable);
        } else {
            if(studentFilterDTO.getSortBy().equalsIgnoreCase("nameAr")){
                    studentFilterDTO.setSortBy("name_ar");
            }else if(studentFilterDTO.getSortBy().equalsIgnoreCase("collegeId")){
                studentFilterDTO.setSortBy("college_id");

            }else if(studentFilterDTO.getSortBy().equalsIgnoreCase("departmentId")){

                studentFilterDTO.setSortBy("department_id");

            }else if(studentFilterDTO.getSortBy().equalsIgnoreCase("levels")){
                studentFilterDTO.setSortBy("level");

            }
            else if(studentFilterDTO.getSortBy().equalsIgnoreCase("universityId")){
                studentFilterDTO.setSortBy("university_id");

            }
            Sort sort2 = constructSortObject2(studentFilterDTO);
            Pageable pageable2 = PageRequest.of(pageUtil.getPage() - 1, pageUtil.getLimit(),sort2);
            studentPage = studentRepository.findAllStudentss(pageable2);
        }
        PageResult<Student> pageResult = new PageResult<>(studentPage.getContent(), (int) studentPage.getTotalElements(),
                pageUtil.getLimit(), pageUtil.getPage());

        return studentMapper.toDataPage(pageResult);
    }

    private Sort constructSortObject(StudentFilterDTO studentFilterDTO) {
        if (studentFilterDTO.getSortDirection() == null) {
            return Sort.by(Sort.Direction.ASC, "nameAr");
        }
        return Sort.by(Sort.Direction.valueOf(studentFilterDTO.getSortDirection()), studentFilterDTO.getSortBy());
    }

    private Sort constructSortObject2(StudentFilterDTO studentFilterDTO) {
        if (studentFilterDTO.getSortDirection() == null) {
            return Sort.by(Sort.Direction.ASC, "name_ar");
        }
        return Sort.by(Sort.Direction.valueOf(studentFilterDTO.getSortDirection()), studentFilterDTO.getSortBy());
    }


}


