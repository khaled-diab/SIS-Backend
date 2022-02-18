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

    @NotNull
    @ManyToMany
    @JoinTable(name = "student_attendance",
            joinColumns = @JoinColumn(name = "attendance_details_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "student_id",referencedColumnName = "id"))
    private List<Student> students;

    @NotNull
    @ManyToMany
    @JoinTable(name = "attendance_lecture",
            joinColumns = @JoinColumn(name = "attendance_details_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "lecture_id",referencedColumnName = "id"))
    private List<Lecture> lectures;

    @NotNull
    @Column(name = "attendance_status")
    private String attendanceStatus;

    @NotNull
    @Column(name = "attendance_date")
    @Temporal(TemporalType.DATE)
    private Date attendanceDate;

    @NotNull
    @Column(name = "lecture_start_time")
    private LocalTime lectureStartTime;

    @NotNull
    @Column(name = "lecture_end_time")
    private LocalTime lectureEndTime;

}
