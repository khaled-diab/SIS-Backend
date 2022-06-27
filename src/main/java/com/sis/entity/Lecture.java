package com.sis.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Time;
import java.time.LocalTime;
import java.util.Collection;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "lecture")
public class Lecture extends BaseEntity{
    private static final long serialVersionUID = 1L;

    //    @NotNull
    @Column(name = "lecture_day")
    private String lectureDay;

    @Column(name = "attendance_type")
    private String attendanceType;

    //    @NotNull
    @Column(name = "lecture_date")
    @Temporal(TemporalType.DATE)
    private Date lectureDate;

    //    @NotNull
    @Column(name = "lecture_start_time")
    @DateTimeFormat(pattern = "HH:mm:ss")
    private Time lectureStartTime;

    //    @NotNull
    @Column(name = "lecture_end_time")
    private Time lectureEndTime;

    @Column(name = "attendance_code")
    private long attendanceCode;

    @Column(name = "attendance_status")
    private boolean attendanceStatus;

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


    @JoinColumn(name = "section_id",referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Section section;


    @OneToMany(mappedBy = "lecture" )
    private Collection<AttendanceDetails> attendanceDetails;

    public boolean getAttendanceStatus() {
        return attendanceStatus;
    }

    public void setAttendanceStatus(boolean attendanceStatus) {
        this.attendanceStatus = attendanceStatus;
    }
}
