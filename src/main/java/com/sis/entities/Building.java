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
@Table(name = "building")
public class Building extends BaseEntity {

    private static final long serialVersionUID = 1L;
   
    @Column(name = "code")
    private String code;
    @Column(name = "name")
    private String name;
    @OneToMany(mappedBy = "buildingId")
    private Collection<Classroom> classroomCollection;
    @JoinColumn(name = "colage_id", referencedColumnName = "id")
    @ManyToOne
    private Colage colageId; 

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

    @XmlTransient
    public Collection<Classroom> getClassroomCollection() {
        return classroomCollection;
    }

    public void setClassroomCollection(Collection<Classroom> classroomCollection) {
        this.classroomCollection = classroomCollection;
    }

    public Colage getColageId() {
        return colageId;
    }

    public void setColageId(Colage colageId) {
        this.colageId = colageId;
    }  
    
}
