package com.sis.repository;

import com.sis.entity.Course;
import com.sis.entity.FacultyMember;
import com.sis.entity.Lecture;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public interface LectureRepository extends BaseDao<Lecture> {
    @Query(value = "SELECT lecture_id FROM section_lecture WHERE  section_id= :sectionId  ", nativeQuery = true)
    Collection<Long> findLecturesBySections(long sectionId);


    @Query(value = "SELECT lecture_id FROM section_lecture WHERE section_id =:sectionId", nativeQuery = true)
    ArrayList<Long> findFacultyMemberLectures(long sectionId);

    ArrayList<Lecture> findLectureByLectureDateAndCourseIdAndFacultyMemberIdAndLectureStartTimeAndLectureEndTime(Date lectureDate,
                                                                                                                 Course course,
                                                                                                                 FacultyMember facultyMember,
                                                                                                                 LocalTime lectureStartTime,
                                                                                                                 LocalTime lectureEndTime);


}
