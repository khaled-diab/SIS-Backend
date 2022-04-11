package com.sis.dao;

import com.sis.entities.AttendanceDetails;
import com.sis.entities.Lecture;
import com.sis.entities.StudentEnrollment;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;
import java.util.Collection;

public interface AttendanceDetailsRepository extends BaseDao<AttendanceDetails>{


    @Query(value="SELECT * FROM attendance_details WHERE  student_id= :studentId and course_id=:courseId ", nativeQuery = true)
    public ArrayList<AttendanceDetails> findStudentAttendances(long studentId, long courseId);

    //@Query(value="SELECT * FROM attendance_details WHERE  lecture_id= :lectureId", nativeQuery = true)
    public ArrayList<AttendanceDetails> findAttendanceDetailsByLectureId(long lectureId);

    // this function is written by Abdo Ramadan
    @Query(value="SELECT * FROM attendance_details WHERE  lecture_id= :lectureId", nativeQuery = true)
    public ArrayList<AttendanceDetails> findAttendanceDetailsByLectureId(Long lectureId);

}
