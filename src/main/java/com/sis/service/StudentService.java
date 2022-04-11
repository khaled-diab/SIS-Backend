package com.sis.service;

import com.sis.dao.StudentEnrollmentRepository;
import com.sis.dao.StudentRepository;
import com.sis.dao.specification.StudentSpecification;
import com.sis.dto.student.StudentDTO;
import com.sis.dto.student.StudentFilterDTO;
import com.sis.entities.mapper.StudentMapper;
import com.sis.entity.Student;
import com.sis.entity.StudentEnrollment;
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
        return this.studentRepository.findByNationalId(id);
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

        Pageable pageable = PageRequest.of(pageUtil.getPage() - 1, pageUtil.getLimit(), constructSortObject(studentFilterDTO));
        if ((searchValue != null && !searchValue.trim().isEmpty()) || filterCollege != null ||
                filterDepartment != null || (filterLevel != null && !filterLevel.trim().isEmpty())) {
            System.out.println("khaled");
            StudentSpecification studentSpecification = new StudentSpecification(searchValue, filterCollege, filterDepartment, filterLevel);

            studentPage = studentRepository.findAll(studentSpecification, pageable);
        } else {
            studentPage = studentRepository.findAll(pageable);
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


}


