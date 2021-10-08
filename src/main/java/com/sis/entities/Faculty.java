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
@Table(name = "faculty")
public class Faculty extends BaseEntity {

    private static final long serialVersionUID = 1L;   
    @Column(name = "name_ar")
    private String nameAr;
    @Column(name = "name_en")
    private String nameEn;
    @Column(name = "Nationality")
    private String nationality;
    @Column(name = "NationalID")
    private String nationalID;
    @Column(name = "birth_date")
    @Temporal(TemporalType.DATE)
    private Date birthDate;   
    @Column(name = "alternative_mail")
    private String alternativeMail;
    @Column(name = "phone")
    private String phone;
    @Column(name = "degree")
    private String degree;
    @JoinColumn(name = "colage_id", referencedColumnName = "id")
    @ManyToOne
    private Colage colageId;
    @JoinColumn(name = "dept_id", referencedColumnName = "id")
    @ManyToOne
    private Departemnt deptId;

    public Faculty() {
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
