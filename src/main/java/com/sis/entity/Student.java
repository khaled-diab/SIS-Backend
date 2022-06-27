/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sis.entity;


import com.sis.entity.security.User;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "student")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Student extends BaseEntity {

    private static final long serialVersionUID = 1L;
    @Column(name = "university_id")
    private long universityId;
    @NotNull
    @Column(name = "name_ar")
    private String nameAr;

    @Column(name = "name_en")
    private String nameEn;

    @Column(name = "nationality")
    private String nationality;

    @Column(name = "nationalID")
    private String nationalId;

    @Column(name = "birth_date")
    @Temporal(TemporalType.DATE)
    private Date birthDate;

    @Column(name = "university_mail")
    private String universityMail;

    @Column(name = "alternative_mail")
    private String alternativeMail;

    @Column(name = "phone")
    private String phone;

    @Column(name = "parent_phone")
    private String parentPhone;
    @Column(name = "level")
    private  String level;

    @Column(name = "year")
    private  String year;

    @Column(name = "photo")
    private  String photo;

    @JoinColumn(name = "academic_program_id", referencedColumnName = "id")
    @ManyToOne
    private AcademicProgram programId;

    @JoinColumn(name = "college_id", referencedColumnName = "id")
    @ManyToOne
    private College collegeId;

    @JoinColumn(name = "department_id", referencedColumnName = "id")
    @ManyToOne
    private Department departmentId;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;


    @OneToMany(mappedBy = "student",cascade = CascadeType.ALL)
    private Collection<StudentEnrollment> studentEnrollments;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private Collection<AttendanceDetails> attendanceDetails;

}
