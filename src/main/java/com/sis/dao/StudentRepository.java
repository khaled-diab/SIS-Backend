package com.sis.dao;

import com.sis.entities.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin("http://localhost:4200")
public interface StudentRepository extends BaseDao<Student>{

    @Query(value="SELECT * FROM student  WHERE college_id= :collegeId", nativeQuery = true)
    public Page<Student> findStudentsByCollage(long collegeId, Pageable pageable);
    @Query(value="SELECT * FROM student  ", nativeQuery = true)
    public Page<Student> findAllStudent(Pageable pageable);

    @Query(value="SELECT * FROM student WHERE college_id= :collegeId and department_id= :departmentId", nativeQuery = true)
    public Page<Student> findStudentsByCollage(long collegeId, long departmentId, Pageable pageable);


    @Query(value="SELECT * FROM student s WHERE   s.name_en LIKE %:att% OR s.name_ar LIKE %:att% OR s.year LIKE %:att% OR s.university_id =:num  ", nativeQuery = true)
    public Page<Student> searchStudent(String att, long num, Pageable pageable);


    @Query(value="SELECT * FROM student s WHERE   s.level=:level and (s.name_en LIKE %:att% OR s.name_ar LIKE %:att% OR s.year LIKE %:att% OR s.university_id =:num ) ", nativeQuery = true)
    public Page<Student> searchStudentByLevel(String att, long num,String level, Pageable pageable);

    @Query(value="SELECT * FROM student s WHERE  s.college_id= :collegeId and ( s.name_en LIKE %:att% OR s.name_ar LIKE %:att% OR s.year LIKE %:att% OR s.university_id = :num )", nativeQuery = true)
    public Page<Student> searchStudent(String att, long num, long collegeId,Pageable pageable);

    @Query(value="SELECT * FROM student s WHERE  s.college_id= :collegeId and s.level=:level and ( s.name_en LIKE %:att% OR s.name_ar LIKE %:att% OR s.year LIKE %:att% OR s.university_id = :num )", nativeQuery = true)
    public Page<Student> searchStudentByLevel(String att, long num, long collegeId,String level,Pageable pageable);

    @Query(value="SELECT * FROM student s WHERE s.college_id= :collegeId and department_id = :departmentId and (  s.name_en LIKE %:att% OR s.name_ar LIKE %:att% OR s.year LIKE %:att% OR s.university_id =:num ) ", nativeQuery = true)
    public Page<Student> searchStudent(String att, long num, long collegeId, long departmentId, Pageable pageable);

    @Query(value="SELECT * FROM student s WHERE s.college_id= :collegeId and department_id = :departmentId and level =:level and (  s.name_en LIKE %:att% OR s.name_ar LIKE %:att% OR s.year LIKE %:att% OR s.university_id =:num ) ", nativeQuery = true)
    public Page<Student> searchStudent(String att, long num, long collegeId, long departmentId, String level,Pageable pageable);

    @Query(value="SELECT * FROM student s WHERE s.university_id= :id ", nativeQuery = true)
    public Student findByUniversityId(long id);

    @Query(value="SELECT * FROM student s WHERE s.nationalid= :id ", nativeQuery = true)
    public Student findByNationalId(String id);

    @Query(value="SELECT * FROM student s WHERE s.university_mail= :mail ", nativeQuery = true)
    public Student findByUniversityMail(String mail);

    @Query(value="SELECT * FROM student s WHERE s.level= :level ", nativeQuery = true)
    public Page<Student> findStudentByLevel(String level,Pageable pageable);

    @Query(value="SELECT * FROM student s WHERE s.college_id= :collegeId and level = :level ", nativeQuery = true)
    public Page<Student> CollegeIdLevel(long collegeId,String level,Pageable pageable);

    @Query(value="SELECT * FROM student s WHERE s.college_id= :collegeId and department_id=:departmentId and level = :level ", nativeQuery = true)
    public Page<Student> findByCollegeIdAndDepartmentIdAndLevel(long collegeId,long departmentId,String level,Pageable pageable);


}
