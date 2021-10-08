/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sis.entities;

import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "academic_progrm")
public class AcademicProgrm extends BaseEntity {

    private static final long serialVersionUID = 1L;
   
    @Column(name = "code")
    private String code;
    @Column(name = "name_arb")
    private String nameArb;
    @Column(name = "name_en")
    private String nameEn;
    @OneToMany(mappedBy = "progId")
    private Collection<Studnet> studnetCollection;
    @JoinColumn(name = "colage_id", referencedColumnName = "id")
    @ManyToOne
    private Colage colageId;
    @JoinColumn(name = "dept_id", referencedColumnName = "id")
    @ManyToOne
    private Departemnt deptId;

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
