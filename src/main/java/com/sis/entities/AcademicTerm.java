/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sis.entities;

import java.util.Date;
import java.util.Optional;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "academic_term")
public class AcademicTerm extends BaseEntity {

    private static final long serialVersionUID = 1L;
   
    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    @Column(name = "start_date")
    @Temporal(TemporalType.DATE)
    private Date startDate;

    @Column(name = "end_date")
    @Temporal(TemporalType.DATE)
    private Date endDate;

    @JoinColumn(name = "academic_year", referencedColumnName = "id")
    @ManyToOne
    private Optional<AcademicYear> academicYear;
   
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Optional<AcademicYear> getAcademicYear() {
        return academicYear;
    }

    public void setAcademicYear(Optional<AcademicYear> academicYear) {
        this.academicYear = academicYear;
    }  
    
}
