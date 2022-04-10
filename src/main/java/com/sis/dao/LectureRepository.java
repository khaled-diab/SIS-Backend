package com.sis.dao;

import com.sis.entities.Course;
import com.sis.entities.FacultyMember;
import com.sis.entities.Lecture;
import org.springframework.data.jpa.repository.Query;

import javax.validation.constraints.NotNull;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public interface LectureRepository extends BaseDao<Lecture> {

    @Query(value="SELECT id FROM lecture WHERE section_id =:sectionId", nativeQuery = true)
    public ArrayList<Long> findFacultyMemberLectures(long sectionId);

    public ArrayList<Lecture> findLectureBySectionIdAndLectureDateAndCourseIdAndFacultyMemberIdAndLectureStartTimeAndLectureEndTime(long SectionId,Date lectureDate,
                                                                                                                        Course course,
                                                                                                                        FacultyMember facultyMember,
                                                                                                                        LocalTime lectureStartTime,
                                                                                                                        LocalTime lectureEndTime);



}
