package com.sis.repository;

import com.sis.entity.Course;
import com.sis.entity.FacultyMember;
import com.sis.entity.Lecture;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;

public interface LectureRepository extends BaseRepository<Lecture> {

    @Query(value = "SELECT id FROM lecture WHERE section_id =:sectionId", nativeQuery = true)
    ArrayList<Long> findFacultyMemberLectures(long sectionId);

    ArrayList<Lecture> findLectureBySectionIdAndLectureDateAndCourseIdAndFacultyMemberIdAndLectureStartTimeAndLectureEndTime(long SectionId, Date lectureDate,
                                                                                                                             Course course,
                                                                                                                             FacultyMember facultyMember,
                                                                                                                             String lectureStartTime,
                                                                                                                             String lectureEndTime);


}
