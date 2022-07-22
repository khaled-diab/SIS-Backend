package com.sis.repository;

import com.sis.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Optional;

@CrossOrigin("http://localhost:4200")
public interface StudentRepository extends BaseRepository<Student> {


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
    @Query(value = "INSERT INTO student ( name_ar, nationality,nationalid,college_id,department_id) VALUES (?1, ?2, ?3, ?4,?5) ",
            nativeQuery = true)
    void saveNativeStudent(String nameAR, String nationality, String nationalID, Long collegeID, Long departmentID);

    Student findByNationalId(String nationalID);

    Boolean existsByNationalId(String nationalID);

    Boolean existsByUniversityMail(String mail);

    Boolean existsByUniversityId(Long universityID);

    Boolean existsByPhone(String phone);

    @Query(value = "SELECT * FROM student s WHERE s.user_id is not null ", nativeQuery = true)
    Page<Student> findAllStudentss(Pageable pageable);

    Student findStudentByUserId(Long userId);

    Long countStudentsByUser_IdNotNull();

    Long countStudentsByUser_IdIsNull();
}
