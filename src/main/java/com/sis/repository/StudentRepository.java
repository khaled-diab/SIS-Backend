package com.sis.repository;

import com.sis.entity.Student;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Optional;

@CrossOrigin("http://localhost:4200")
public interface StudentRepository extends Baserepository<Student> {


    @Query(value = "SELECT * FROM student s WHERE s.university_id= :id ", nativeQuery = true)
    Student findByUniversityId(long id);

    @Query(value = "SELECT * FROM student s WHERE s.nationalid= :id ", nativeQuery = true)
    Student findByNationalIdNative(String id);

    @Query(value = "SELECT * FROM student s WHERE s.university_mail= :mail ", nativeQuery = true)
    Student findByUniversityMail(String mail);

    Optional<Student> findByUser_Id(Long userID);

//    @Query(value = "INSERT INTO student ( name_ar, name_en, nationality,nationalid,birth_date,college_id,department_id)" +
//            "VALUES (?1, ?2, ?3, ?4,?5,?6,?7); ", nativeQuery = true)
//    void saveNativeStudent(String nameAR, String nameEN, String nationality, String nationalID, Date birthDate, Long collegeID, Long departmentID);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO student ( name_ar, name_en, nationality,nationalid,college_id,department_id,university_id) VALUES (?1, ?2, ?3, ?4,?5,?6,?7) ",
            nativeQuery = true)
    void saveNativeStudent(String nameAR, String nameEN, String nationality, String nationalID, Long collegeID, Long departmentID, Long universityID);

    Optional<Student> findByNationalId(String nationalID);

}
