package com.sis.dao;

import com.sis.entities.FacultyMemberEnrollment;
import com.sis.entities.Timetable;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;

public interface TimetableRepository extends BaseDao<Timetable>{


    //UC011
    @Query(value="SELECT * FROM timetable WHERE academic_year_id =:academicYearId and  academic_term_id=:academicTermId and faculty_member_id= :facultyMemberId  and course_id =:courseId", nativeQuery = true)
    public Collection<Timetable> findFacultyMemberTimeTables(long academicYearId, long academicTermId, long facultyMemberId, long courseId);
}
