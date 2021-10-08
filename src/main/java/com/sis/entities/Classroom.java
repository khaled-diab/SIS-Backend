/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sis.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "classroom")

public class Classroom extends BaseEntity {

    private static final long serialVersionUID = 1L;
   
    @Column(name = "code")
    private String code;
    @Column(name = "name")
    private String name;
    @JoinColumn(name = "building_id", referencedColumnName = "id")
    @ManyToOne
    private Building buildingId;
   
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

    public Building getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(Building buildingId) {
        this.buildingId = buildingId;
    }   
    
}
