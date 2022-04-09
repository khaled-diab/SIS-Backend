package com.sis.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "attendance_details")
public class AttendanceDetails extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @ManyToOne
    @JoinColumn(name = "student_id", referencedColumnName = "id")
    private Student student;


    @ManyToOne
    @JoinColumn(name = "lecture_id", referencedColumnName = "id")
    private Lecture lecture;
    @ManyToOne
    @JoinColumn(name = "section_id", referencedColumnName = "id")
    private Section section;


    @Column(name = "attendance_status")
    private String attendanceStatus;


    @Column(name = "attendance_date")
    @Temporal(TemporalType.DATE)
    private Date attendanceDate;


    @Column(name = "lecture_start_time")
    private LocalTime lectureStartTime;


    @Column(name = "lecture_end_time")
    private LocalTime lectureEndTime;

}
