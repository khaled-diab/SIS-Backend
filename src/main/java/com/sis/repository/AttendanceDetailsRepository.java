package com.sis.repository;

import com.sis.entity.AttendanceDetails;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;

public interface AttendanceDetailsRepository extends BaseRepository<AttendanceDetails> {


    @Query(value = "SELECT * FROM attendance_details WHERE  student_id= :studentId and" +
            " course_id=:courseId ", nativeQuery = true)
    ArrayList<AttendanceDetails> findStudentAttendances(long studentId, long courseId);

    @Query(value = "SELECT id FROM attendance_details WHERE  student_id= :studentId and" +
            " section_id=:sectionId and attendance_status='Absent'", nativeQuery = true)
    ArrayList<Long> findStudentAbsenceLecture(long studentId, long sectionId);

    //@Query(value="SELECT * FROM attendance_details WHERE  lecture_id= :lectureId", nativeQuery = true)
    ArrayList<AttendanceDetails> findAttendanceDetailsByLectureId(long lectureId);

    // this function is written by Abdo Ramadan
    @Query(value = "SELECT * FROM attendance_details WHERE  lecture_id= :lectureId", nativeQuery = true)
    ArrayList<AttendanceDetails> findAttendanceDetailsByLectureId(Long lectureId);

    // this function is written by Abdo Ramadan
    @Query(value = "SELECT * FROM attendance_details WHERE  section_id= :sectionId and " +
            "section_id=:studnetId", nativeQuery = true)
    ArrayList<AttendanceDetails> findAttendanceDetailsBySectionIdAndStudentId(Long sectionId, Long studnetId);

    @Query(value = "SELECT * FROM attendance_details WHERE  section_id= :sectionId", nativeQuery = true)
    ArrayList<AttendanceDetails> findStudentBySectionId(Long sectionId);

}
