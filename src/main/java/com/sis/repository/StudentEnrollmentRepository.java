package com.sis.repository;


import com.sis.entity.AcademicTerm;
import com.sis.entity.AcademicYear;
import com.sis.entity.StudentEnrollment;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;

public interface StudentEnrollmentRepository extends BaseRepository<StudentEnrollment> {

    //Abdo.Amr
    // @Query(value="SELECT * FROM student_enrollment WHERE academic_year_id =:academicYearId and  academic_term_id=:academicTermId and  student_id= :studentId  ", nativeQuery = true)
    ArrayList<StudentEnrollment> findStudentEnrollmentByAcademicYearAndAcademicTermAndStudentId(AcademicYear academicYear, AcademicTerm academicTerm, long studentId);

    int countAllBySectionId(long sectionId);

    //Abdo.Amr
    @Query(value = "SELECT * FROM student_enrollment WHERE academic_year_id =:academicYearId and  academic_term_id=:academicTermId and  student_id= :studentId and course_id=:courseId ", nativeQuery = true)
    StudentEnrollment findStudentSection(long academicYearId, long academicTermId, long studentId, long courseId);

    //Abdo.Amr
    @Query(value = "SELECT * FROM student_enrollment WHERE academic_year_id =:academicYearId and  academic_term_id=:academicTermId and student_id= :studentId  ", nativeQuery = true)
    ArrayList<StudentEnrollment> findStudentCourses(long academicYearId, long academicTermId, long studentId);

    //Abdo.Amr
    @Query(value = "SELECT * FROM student_enrollment WHERE academic_year_id =:academicYearId and  academic_term_id=:academicTermId and section_id= :sectionId  ", nativeQuery = true)
    ArrayList<StudentEnrollment> findStudentsBySection(long academicYearId, long academicTermId, long sectionId);


}
