package com.sis.dao;

import com.sis.entities.Timetable;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;
import java.util.Collection;

public interface TimetableRepository extends BaseDao<Timetable>{

    //Abdo.Amr
    @Query(value="SELECT * FROM timetable WHERE academic_year_id =:academicYearId and  academic_term_id=:academicTermId and faculty_member_id= :facultyMemberId  and course_id =:courseId", nativeQuery = true)
    public Collection<Timetable> findFacultyMemberTimeTables(long academicYearId, long academicTermId, long facultyMemberId, long courseId);

    //Abdo.Amr
    @Query(value="SELECT distinct section_id FROM timetable WHERE academic_year_id =:academicYearId and  academic_term_id=:academicTermId and faculty_member_id= :facultyMemberId  ", nativeQuery = true)
    public ArrayList<Long> findFacultyMemberSections(long academicYearId, long academicTermId, long facultyMemberId);

    //Abdo.Amr
    @Query(value="SELECT distinct course_id FROM timetable WHERE academic_year_id =:academicYearId and  academic_term_id=:academicTermId and faculty_member_id= :facultyMemberId  ", nativeQuery = true)
    public ArrayList<Long> findFacultyMemberCourses(long academicYearId, long academicTermId, long facultyMemberId);

    //Abdo.Amr
    @Query(value="SELECT * FROM timetable WHERE academic_year_id =:academicYearId and  academic_term_id=:academicTermId and section_id= :sectionId  ", nativeQuery = true)
    public ArrayList<Timetable> findTimetableBySection(long academicYearId, long academicTermId,long sectionId);

}
