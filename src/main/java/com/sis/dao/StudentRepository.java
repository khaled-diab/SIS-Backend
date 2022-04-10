package com.sis.dao;

import com.sis.entities.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin("http://localhost:4200")
public interface StudentRepository extends BaseDao<Student> {


    @Query(value = "SELECT * FROM student s WHERE s.university_id= :id ", nativeQuery = true)
    public Student findByUniversityId(long id);

    @Query(value = "SELECT * FROM student s WHERE s.nationalid= :id ", nativeQuery = true)
    public Student findByNationalId(String id);

    @Query(value = "SELECT * FROM student s WHERE s.university_mail= :mail ", nativeQuery = true)
    public Student findByUniversityMail(String mail);

}
