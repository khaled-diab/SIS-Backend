/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sis.entity;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlTransient;
import java.util.Collection;

@Entity
@Table(name = "department")
public class Department extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Column(name = "code", unique = true)
    private String code;

    @Column(name = "name_ar")
    private String nameAr;

    @Column(name = "name_en")
    private String nameEn;

    @OneToMany(mappedBy = "departmentId")
    private Collection<Student> studentCollection;

    @JoinColumn(name = "college_id", referencedColumnName = "id")
    @ManyToOne
    private College collegeId;

    @OneToMany(mappedBy = "departmentId")
    private Collection<AcademicProgram> academicProgramCollection;

    @OneToMany(mappedBy = "department")
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

    public College getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(College collegeId) {
        this.collegeId = collegeId;
    }

    @XmlTransient
    public Collection<AcademicProgram> getAcademicProgramCollection() {
        return academicProgramCollection;
    }

    public void setAcademicProgramCollection(Collection<AcademicProgram> academicProgramCollection) {
        this.academicProgramCollection = academicProgramCollection;
    }

    @XmlTransient
    public Collection<FacultyMember> getFacultyCollection() {
        return facultyMemberCollection;
    }

    public void setFacultyCollection(Collection<FacultyMember> facultyMemberCollection) {
        this.facultyMemberCollection = facultyMemberCollection;
    }

}
