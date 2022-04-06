package com.sis.dao;

import com.sis.entities.StudentEnrollment;
import com.sis.entities.*;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;

public interface StudentEnrollmentRepository extends BaseDao<StudentEnrollment> {

    //Abdo.Amr
    // @Query(value="SELECT * FROM student_enrollment WHERE academic_year_id =:academicYearId and  academic_term_id=:academicTermId and  student_id= :studentId  ", nativeQuery = true)
    public ArrayList<StudentEnrollment> findStudentEnrollmentByAcademicYearAndAcademicTermAndStudentId(AcademicYear academicYear, AcademicTerm academicTerm, long studentId);

    ArrayList<StudentEnrollment> findStudentEnrollmentByStudentId(long studentId);

    int countAllBySectionId(long sectionId);

    //Abdo.Amr
    @Query(value = "SELECT * FROM student_enrollment WHERE academic_year_id =:academicYearId and  academic_term_id=:academicTermId and  student_id= :studentId and course_id=:courseId ", nativeQuery = true)
    public StudentEnrollment findStudentSection(long academicYearId, long academicTermId, long studentId, long courseId);

    //Abdo.Amr
    @Query(value = "SELECT * FROM student_enrollment WHERE academic_year_id =:academicYearId and  academic_term_id=:academicTermId and student_id= :studentId  ", nativeQuery = true)
    public ArrayList<StudentEnrollment> findStudentCourses(long academicYearId, long academicTermId, long studentId);

    //Abdo.Amr
    @Query(value = "SELECT * FROM student_enrollment WHERE academic_year_id =:academicYearId and  academic_term_id=:academicTermId and section_id= :sectionId  ", nativeQuery = true)
    public ArrayList<StudentEnrollment> findStudentsBySection(long academicYearId, long academicTermId, long sectionId);


}
