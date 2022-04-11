package com.sis.repository;

import com.sis.entity.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin("http://localhost:4200")
public interface StudentRepository extends BaseDao<Student> {


    @Query(value = "SELECT * FROM student s WHERE s.university_id= :id ", nativeQuery = true)
    Student findByUniversityId(long id);

    @Query(value = "SELECT * FROM student s WHERE s.nationalid= :id ", nativeQuery = true)
    Student findByNationalId(String id);

    @Query(value = "SELECT * FROM student s WHERE s.university_mail= :mail ", nativeQuery = true)
    Student findByUniversityMail(String mail);

}
