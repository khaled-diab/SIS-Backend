package com.sis.dao;

import com.sis.entities.FacultyMemberEnrollment;
import com.sis.entities.StudentEnrollment;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;

public interface StudentEnrollmentRepository extends BaseDao<StudentEnrollment>{

    @Query(value="SELECT student_enrollment_id FROM student_enrollment_student WHERE  student_id= :studentId  ", nativeQuery = true)
    public Collection<Long> findStudentEnrollmentsByStudent(long studentId);




}
