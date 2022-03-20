package com.sis.dao;

import com.sis.entities.FacultyMemberEnrollment;
import com.sis.entities.Section;
import com.sis.entities.StudentEnrollment;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;
import java.util.Collection;

public interface StudentEnrollmentRepository extends BaseDao<StudentEnrollment>{

    //UC011
    @Query(value="SELECT * FROM student_enrollment WHERE academic_year_id =:academicYearId and  academic_term_id=:academicTermId and  student_id= :studentId  ", nativeQuery = true)
    public ArrayList<StudentEnrollment> findStudentSections(long academicYearId, long academicTermId, long studentId);

    //UC011
    @Query(value="SELECT * FROM student_enrollment WHERE" +
            " academic_year_id =:academicYearId and  " +
            "academic_term_id=:academicTermId and  " +
            "student_id= :studentId and course_id=:courseId ", nativeQuery = true)
    public StudentEnrollment findStudentSection(long academicYearId, long academicTermId, long studentId, long courseId);

    //UC011
    @Query(value="SELECT * FROM student_enrollment WHERE " +
            "academic_year_id =:academicYearId and  academic_term_id=:academicTermId and " +
            "student_id= :studentId  ", nativeQuery = true)
    public ArrayList<StudentEnrollment> findStudentCourses(long academicYearId, long academicTermId, long studentId);



}
