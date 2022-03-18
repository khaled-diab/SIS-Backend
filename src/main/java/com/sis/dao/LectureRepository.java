package com.sis.dao;

import com.sis.entities.Course;
import com.sis.entities.FacultyMember;
import com.sis.entities.Lecture;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public interface LectureRepository extends BaseDao<Lecture> {
    @Query(value="SELECT lecture_id FROM section_lecture WHERE  section_id= :sectionId  ", nativeQuery = true)
    public Collection<Long> findLecturesBySections(long sectionId);

    //UC011
    @Query(value="SELECT lecture_id FROM section_lecture WHERE section_id =:sectionId", nativeQuery = true)
    public ArrayList<Long> findFacultyMemberLectures(long sectionId);

    public ArrayList<Lecture> findLectureByLectureDateAndCourseIdAndFacultyMemberIdAndLectureStartTimeAndLectureEndTime(Date lectureDate,
                                                                                                                         Course course,
                                                                                                                         FacultyMember facultyMember,
                                                                                                                        LocalTime lectureStartTime,
                                                                                                                         LocalTime lectureEndTime);


}
