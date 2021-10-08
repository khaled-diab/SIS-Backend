/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sis.entities;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "studnet")
public class Studnet extends BaseEntity {

    private static final long serialVersionUID = 1L;   
    @Column(name = "name_arb")
    private String nameArb;
    @Column(name = "name_en")
    private String nameEn;
    @Column(name = "Nationality")
    private String nationality;
    @Column(name = "NationalID")
    private String nationalID;
    @Column(name = "birth_date")
    @Temporal(TemporalType.DATE)
    private Date birthDate;
    @Column(name = "UniversityMail")
    private String universityMail;
    @Column(name = "AlternativeMail")
    private String alternativeMail;
    @Column(name = "phone")
    private String phone;
    @Column(name = "parent_phone")
    private String parentPhone;
    @JoinColumn(name = "prog_id", referencedColumnName = "id")
    @ManyToOne
    private AcademicProgrm progId;
    @JoinColumn(name = "colage_id", referencedColumnName = "id")
    @ManyToOne
    private Colage colageId;
    @JoinColumn(name = "dept_id", referencedColumnName = "id")
    @ManyToOne
    private Departemnt deptId;   

    public String getNameArb() {
        return nameArb;
    }

    public void setNameArb(String nameArb) {
        this.nameArb = nameArb;
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

    public String getUniversityMail() {
        return universityMail;
    }

    public void setUniversityMail(String universityMail) {
        this.universityMail = universityMail;
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

    public String getParentPhone() {
        return parentPhone;
    }

    public void setParentPhone(String parentPhone) {
        this.parentPhone = parentPhone;
    }

    public AcademicProgrm getProgId() {
        return progId;
    }

    public void setProgId(AcademicProgrm progId) {
        this.progId = progId;
    }

    public Colage getColageId() {
        return colageId;
    }

    public void setColageId(Colage colageId) {
        this.colageId = colageId;
    }

    public Departemnt getDeptId() {
        return deptId;
    }

    public void setDeptId(Departemnt deptId) {
        this.deptId = deptId;
    }
   
}
