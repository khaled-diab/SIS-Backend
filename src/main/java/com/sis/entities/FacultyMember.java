/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sis.entities;

import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name = "faculty_member")
public class FacultyMember extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Column(name = "name_ar", unique = true, nullable = false)
    private String nameAr;

    @Column(name = "name_en", unique = true, nullable = false)
    private String nameEn;

    @Column(name = "nationality")
    private String nationality;

    @Column(name = "nationalID", length = 14, nullable = false, unique = true)
    private String nationalID;

    @Column(name = "birth_date")
    @Temporal(TemporalType.DATE)
    private Date birthDate;

    @Column(name = "university_mail", unique = true, nullable = false)
    private String universityMail;

    @Column(name = "alternative_mail",unique = true)
    private String alternativeMail;

    @Column(name = "phone")
    private String phone;

    @Column(name = "degree", nullable = false)
    private String degree;

    @ManyToOne
    @JoinColumn(name = "collegeId", referencedColumnName = "id")
    private College collegeId;

    @ManyToOne
    @JoinColumn(name = "departmentId", referencedColumnName = "id")
    private Department departmentId;

    public FacultyMember() {
    }

    public String getUniversityMail() {
        return universityMail;
    }

    public void setUniversityMail(String universityMail) {
        this.universityMail = universityMail;
    }

    public String getNameAr() {
        return nameAr;
    }

    public void setNameAr(String nameAr) {
        this.nameAr = nameAr;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getNationalID() {
        return nationalID;
    }

    public void setNationalID(String nationalID) {
        this.nationalID = nationalID;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

	public String getAlternativeMail() {
        return alternativeMail;
    }

    public void setAlternativeMail(String alternativeMail) {
        this.alternativeMail = alternativeMail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public College getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(College collegeId) {
        this.collegeId = collegeId;
    }

    public Department getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Department deptId) {
        this.departmentId = deptId;
    } 
    
}
