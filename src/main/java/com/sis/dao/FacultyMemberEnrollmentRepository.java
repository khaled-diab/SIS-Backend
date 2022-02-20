package com.sis.dao;

import com.sis.entities.FacultyMemberEnrollment;

import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FacultyMemberEnrollmentRepository extends BaseDao<FacultyMemberEnrollment>{


    @Query(value="SELECT * FROM faculty_member_enrollment WHERE academic_year_id =:academicYearId and  academic_term_id=:academicTermId and faculty_member_id= :facultyMemberId  ", nativeQuery = true)
    public FacultyMemberEnrollment findByFacultyMember(long academicYearId, long academicTermId, long facultyMemberId);

}
