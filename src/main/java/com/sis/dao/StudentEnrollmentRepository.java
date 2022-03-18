package com.sis.dao;

import com.sis.entities.*;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;
import java.util.Collection;

public interface StudentEnrollmentRepository extends BaseDao<StudentEnrollment>{

    //UC011
   // @Query(value="SELECT * FROM student_enrollment WHERE academic_year_id =:academicYearId and  academic_term_id=:academicTermId and  student_id= :studentId  ", nativeQuery = true)
    public ArrayList<StudentEnrollment> findStudentEnrollmentByAcademicYearAndAcademicTermAndStudent(AcademicYear academicYear, AcademicTerm academicTerm, Student student);

    //UC011
    @Query(value="SELECT * FROM student_enrollment WHERE academic_year_id =:academicYearId and  academic_term_id=:academicTermId and  student_id= :studentId and course_id=:courseId ", nativeQuery = true)
    public StudentEnrollment findStudentSection(long academicYearId, long academicTermId, long studentId, long courseId);

    //UC011
    @Query(value="SELECT * FROM student_enrollment WHERE academic_year_id =:academicYearId and  academic_term_id=:academicTermId and student_id= :studentId  ", nativeQuery = true)
    public ArrayList<StudentEnrollment> findStudentCourses(long academicYearId, long academicTermId, long studentId);

    //UC011
    @Query(value="SELECT * FROM student_enrollment WHERE academic_year_id =:academicYearId and  academic_term_id=:academicTermId and section_id= :sectionId  ", nativeQuery = true)
    public ArrayList<StudentEnrollment> findStudentsBySection(long academicYearId, long academicTermId, long sectionId);


}
