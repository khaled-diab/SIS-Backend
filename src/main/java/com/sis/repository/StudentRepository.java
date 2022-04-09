package com.sis.repository;

import com.sis.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Optional;

@CrossOrigin("http://localhost:4200")
public interface StudentRepository extends BaseDao<Student>{

    @Query(value="SELECT * FROM student  WHERE college_id= :collegeId", nativeQuery = true)
    Page<Student> findStudentsByCollage(long collegeId, Pageable pageable);
    @Query(value="SELECT * FROM student  ", nativeQuery = true)
    Page<Student> findAllStudent(Pageable pageable);

    @Query(value="SELECT * FROM student WHERE college_id= :collegeId and department_id= :departmentId", nativeQuery = true)
    Page<Student> findStudentsByCollage(long collegeId, long departmentId, Pageable pageable);


    @Query(value="SELECT * FROM student s WHERE   s.name_en LIKE %:att% OR s.name_ar LIKE %:att% OR s.year LIKE %:att% OR s.university_id =:num  ", nativeQuery = true)
    Page<Student> searchStudent(String att, long num, Pageable pageable);

    @Query(value="SELECT * FROM student s WHERE  s.college_id= :collegeId and ( s.name_en LIKE %:att% OR s.name_ar LIKE %:att% OR s.year LIKE %:att% OR s.university_id = :num )", nativeQuery = true)
    Page<Student> searchStudent(String att, long num, long collegeId, Pageable pageable);

    @Query(value="SELECT * FROM student s WHERE s.college_id= :collegeId and department_id = :departmentId and (  s.name_en LIKE %:att% OR s.name_ar LIKE %:att% OR s.year LIKE %:att% OR s.university_id =:num ) ", nativeQuery = true)
    Page<Student> searchStudent(String att, long num, long collegeId, long departmentId, Pageable pageable);

    Student findByUniversityId(long id);
    Student findByNationalId(String id);
    Student findByUniversityMail(String mail);
    Optional<Student> findByUser_Id(Long userID);


}
