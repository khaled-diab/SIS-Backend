package com.sis.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

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
