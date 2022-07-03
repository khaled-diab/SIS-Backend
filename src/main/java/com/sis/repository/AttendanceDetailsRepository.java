package com.sis.repository;

import com.sis.entity.AttendanceDetails;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;
import java.util.List;

public interface AttendanceDetailsRepository extends BaseRepository<AttendanceDetails> {


    @Query(value = "SELECT * FROM attendance_details WHERE  student_id= :studentId and course_id=:courseId ", nativeQuery = true)
    ArrayList<AttendanceDetails> findStudentAttendances(long studentId, long courseId);

    @Query(value = "SELECT * FROM attendance_details WHERE  course_id=:courseId ", nativeQuery = true)
    ArrayList<AttendanceDetails> findStudentAttendanceByCourseId( long courseId);
    //@Query(value="SELECT * FROM attendance_details WHERE  lecture_id= :lectureId", nativeQuery = true)
    ArrayList<AttendanceDetails> findAttendanceDetailsByLectureId(long lectureId);

    // this function is written by Abdo Ramadan
    @Query(value = "SELECT * FROM attendance_details WHERE  lecture_id= :lectureId", nativeQuery = true)
    ArrayList<AttendanceDetails> findAttendanceDetailsByLectureId(Long lectureId);

    // this function is written by Abdo Ramadan
    @Query(value = "SELECT * FROM attendance_details WHERE  lecture_id= :lectureId and " +
            "section_id=:studnetId", nativeQuery = true)
    ArrayList<AttendanceDetails> findAttendanceDetailsBySectionIdAndStudentId(Long lectureId, Long studnetId);
}
