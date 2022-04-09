package com.sis.repository;

import com.sis.entity.AttendanceDetails;
import com.sis.entity.Lecture;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;

public interface AttendanceDetailsRepository extends BaseDao<AttendanceDetails>{


    @Query(value = "SELECT * FROM attendance_details WHERE  student_id= :studentId and course_id=:courseId ", nativeQuery = true)
    ArrayList<AttendanceDetails> findStudentAttendances(long studentId, long courseId);

    //@Query(value="SELECT * FROM attendance_details WHERE  lecture_id= :lectureId", nativeQuery = true)
    ArrayList<AttendanceDetails> findAttendanceDetailsByLecture(Lecture lecture);

    // this function is written by Abdo Ramadan
    @Query(value = "SELECT * FROM attendance_details WHERE  lecture_id= :lectureId", nativeQuery = true)
    ArrayList<AttendanceDetails> findAttendanceDetailsByLectureId(Long lectureId);

}
