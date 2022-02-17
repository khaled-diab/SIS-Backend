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
@Table(name = "lecture")
public class Lecture extends BaseEntity{
    private static final long serialVersionUID = 1L;

//    @NotNull
    @Column(name = "lecture_day")
    private String lectureDay;

//    @NotNull
    @Column(name = "lecture_date")
    @Temporal(TemporalType.DATE)
    private Date lectureDate;

//    @NotNull
    @Column(name = "lecture_start_time")
    private LocalTime lectureStartTime;

//    @NotNull
    @Column(name = "lecture_end_time")
    private LocalTime lectureEndTime;

    @Column(name = "attendance_code")
    private long attendanceCode;

    @Column(name = "attendance_code_expiring_time")
    private LocalTime attendanceCodeExpiringTime;

    @NotNull
    @JoinColumn(name = "faculty_member_id", referencedColumnName = "id")
    @ManyToOne
    private FacultyMember facultyMemberId;

    @JoinColumn(name = "course_id", referencedColumnName = "id")
    @ManyToOne
    private Course courseId;

    @JoinColumn(name = "academic_term_id", referencedColumnName = "id")
    @ManyToOne
    private AcademicTerm academicTermId;

    @JoinColumn(name = "academic_year_id", referencedColumnName = "id")
    @ManyToOne
    private AcademicYear academicYearId;


    @ManyToMany
    @JoinTable(name = "section_lecture",
            joinColumns = @JoinColumn(name = "lecture_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "section_id",referencedColumnName = "id"))
    private List<Section>  sections;

//    @NotNull
    @ManyToMany
    @JoinTable(name = "attendance_lecture",
            joinColumns = @JoinColumn(name = "lecture_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "attendance_details_id",referencedColumnName = "id"))
    private List<AttendanceDetails> attendanceDetails;
}
