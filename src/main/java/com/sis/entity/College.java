/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sis.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlTransient;
import java.util.Collection;

@Entity
@Table(name = "college")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class College extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Column(name = "code")
    private String code;

    @Column(name = "name_ar")
    private String nameAr;

    @Column(name = "name_en")
    private String nameEn;

    @OneToMany(mappedBy = "collegeId")
    private Collection<Student> studentCollection;

    @OneToMany(mappedBy = "collegeId")
    private Collection<Department> departmentCollection;

    @OneToMany(mappedBy = "collegeId")
    private Collection<AcademicProgram> academicProgramCollection;

    @OneToMany(mappedBy = "collegeId")
    private Collection<Building> buildingCollection;

    @OneToMany(mappedBy = "college")
    private Collection<FacultyMember> facultyMemberCollection;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    @XmlTransient
    public Collection<Student> getStudentCollection() {
        return studentCollection;
    }

    public void setStudentCollection(Collection<Student> studentCollection) {
        this.studentCollection = studentCollection;
    }

    @XmlTransient
    public Collection<Department> getDepartmentCollection() {
        return departmentCollection;
    }

    public void setDepartmentCollection(Collection<Department> departmentCollection) {
        this.departmentCollection = departmentCollection;
    }

    @XmlTransient
    public Collection<AcademicProgram> getAcademicProgramCollection() {
        return academicProgramCollection;
    }

    public void setAcademicProgramCollection(Collection<AcademicProgram> academicProgramCollection) {
        this.academicProgramCollection = academicProgramCollection;
    }

    @XmlTransient
    public Collection<Building> getBuildingCollection() {
        return buildingCollection;
    }

    public void setBuildingCollection(Collection<Building> buildingCollection) {
        this.buildingCollection = buildingCollection;
    }

    @XmlTransient
    public Collection<FacultyMember> getFacultyCollection() {
        return facultyMemberCollection;
    }

    public void setFacultyCollection(Collection<FacultyMember> facultyMemberCollection) {
        this.facultyMemberCollection = facultyMemberCollection;
    }


}
