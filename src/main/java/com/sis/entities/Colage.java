/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sis.entities;

import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "colage")
public class Colage extends BaseEntity {

    private static final long serialVersionUID = 1L;   
    @Column(name = "code")
    private String code;
    @Column(name = "name_arb")
    private String nameArb;
    @Column(name = "name_en")
    private String nameEn;
    @OneToMany(mappedBy = "colageId")
    private Collection<Studnet> studnetCollection;
    @OneToMany(mappedBy = "colageId")
    private Collection<Departemnt> departemntCollection;
    @OneToMany(mappedBy = "colageId")
    private Collection<AcademicProgrm> academicProgrmCollection;
    @OneToMany(mappedBy = "colageId")
    private Collection<Building> buildingCollection;
    @OneToMany(mappedBy = "colageId")
    private Collection<Faculty> facultyCollection;  

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

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

    @XmlTransient
    public Collection<Studnet> getStudnetCollection() {
        return studnetCollection;
    }

    public void setStudnetCollection(Collection<Studnet> studnetCollection) {
        this.studnetCollection = studnetCollection;
    }

    @XmlTransient
    public Collection<Departemnt> getDepartemntCollection() {
        return departemntCollection;
    }

    public void setDepartemntCollection(Collection<Departemnt> departemntCollection) {
        this.departemntCollection = departemntCollection;
    }

    @XmlTransient
    public Collection<AcademicProgrm> getAcademicProgrmCollection() {
        return academicProgrmCollection;
    }

    public void setAcademicProgrmCollection(Collection<AcademicProgrm> academicProgrmCollection) {
        this.academicProgrmCollection = academicProgrmCollection;
    }

    @XmlTransient
    public Collection<Building> getBuildingCollection() {
        return buildingCollection;
    }

    public void setBuildingCollection(Collection<Building> buildingCollection) {
        this.buildingCollection = buildingCollection;
    }

    @XmlTransient
    public Collection<Faculty> getFacultyCollection() {
        return facultyCollection;
    }

    public void setFacultyCollection(Collection<Faculty> facultyCollection) {
        this.facultyCollection = facultyCollection;
    }  
    
}
