package com.sis.repository;

import com.sis.entity.Timetable;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public interface TimetableRepository extends BaseRepository<Timetable> {

    //Abdo.Amr
    @Query(value = "SELECT * FROM timetable WHERE academic_year_id =:academicYearId and  academic_term_id=:academicTermId and faculty_member_id= :facultyMemberId  and course_id =:courseId", nativeQuery = true)
    Collection<Timetable> findFacultyMemberTimeTables(long academicYearId, long academicTermId, long facultyMemberId, long courseId);

    //Abdo.Amr
    @Query(value = "SELECT distinct section_id FROM timetable WHERE academic_year_id =:academicYearId and  academic_term_id=:academicTermId and faculty_member_id= :facultyMemberId  ", nativeQuery = true)
    ArrayList<Long> findFacultyMemberSections(long academicYearId, long academicTermId, long facultyMemberId);

    //Abdo.Amr
    @Query(value = "SELECT distinct course_id FROM timetable WHERE academic_year_id =:academicYearId and  academic_term_id=:academicTermId and faculty_member_id= :facultyMemberId  ", nativeQuery = true)
    ArrayList<Long> findFacultyMemberCourses(long academicYearId, long academicTermId, long facultyMemberId);

    //Abdo.Amr
    @Query(value = "SELECT * FROM timetable WHERE academic_year_id =:academicYearId and  academic_term_id=:academicTermId and section_id= :sectionId  ", nativeQuery = true)
    ArrayList<Timetable> findTimetableBySection(long academicYearId, long academicTermId, long sectionId);

    @Query(value = "SELECT distinct course_id FROM timetable WHERE academic_term_id=:academicTermId and faculty_member_id= :facultyMemberId", nativeQuery = true)
    ArrayList<Long> getTimetablesByAcademicTerm_IdAndFacultyMemberId(Long academicTermId, Long facultyMemberId);

}
