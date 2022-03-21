package com.sis.dao;

import com.sis.entities.Course;
import com.sis.entities.FacultyMember;
import com.sis.entities.FacultyMemberEnrollment;

import com.sis.entities.StudentEnrollment;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public interface FacultyMemberEnrollmentRepository extends BaseDao<FacultyMemberEnrollment>{


    //Abdo.Amr
    @Query(value="SELECT * FROM faculty_member_enrollment WHERE academic_year_id =:academicYearId and  academic_term_id=:academicTermId and faculty_member_id= :facultyMemberId  ", nativeQuery = true)
    public ArrayList<FacultyMemberEnrollment> findFacultyMemberCourses(long academicYearId, long academicTermId, long facultyMemberId);

}
