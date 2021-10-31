/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sis.entities;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "course")
public class Course extends BaseEntity {

    private static final long serialVersionUID = 1L;   
    @Column(name = "code")
    private String code;

    @Column(name = "name_ar")
    private String nameAr;

    @Column(name = "name_en")
    private String nameEn;

    @Column(name = "theoretical_hours")
    private Float theoreticalHours;

    @Column(name = "exercises_hours")
    private Float exercisesHours;

    @Column(name = "practical_hours")
    private Float practicalHours;

    @Column(name = "total_hours")
    private Float totalHours;

    @Column(name = "weeks")
    private Integer weeks;

    @Column(name = "final_grade")
    private Float finalGrade;

    @Column(name = "final_exam_grade")
    private Float finalExamGrade;

    @Column(name = "practical_grade")
    private Float practicalGrade;

    @Column(name = "oral_grade")
    private Float oralGrade;

    @Column(name = "mid_grade")
    private Float midGrade;

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

    public Float getTheoreticalHours() {
        return theoreticalHours;
    }

    public void setTheoreticalHours(Float theoreticalHours) {
        this.theoreticalHours = theoreticalHours;
    }

    public Float getExercisesHours() {
        return exercisesHours;
    }

    public void setExercisesHours(Float exercisesHours) {
        this.exercisesHours = exercisesHours;
    }

    public Float getPracticalHours() {
        return practicalHours;
    }

    public void setPracticalHours(Float practicalHours) {
        this.practicalHours = practicalHours;
    }

    public Float getTotalHours() {
        return totalHours;
    }

    public void setTotalHours(Float totalHours) {
        this.totalHours = totalHours;
    }

    public Integer getWeeks() {
        return weeks;
    }

    public void setWeeks(Integer weeks) {
        this.weeks = weeks;
    }

    public Float getFinalGrade() {
        return finalGrade;
    }

    public void setFinalGrade(Float finalGrade) {
        this.finalGrade = finalGrade;
    }

    public Float getFinalExamGrade() {
        return finalExamGrade;
    }

    public void setFinalExamGrade(Float finalExamGrade) {
        this.finalExamGrade = finalExamGrade;
    }

    public Float getPracticalGrade() {
        return practicalGrade;
    }

    public void setPracticalGrade(Float practicalGrade) {
        this.practicalGrade = practicalGrade;
    }

    public Float getOralGrade() {
        return oralGrade;
    }

    public void setOralGrade(Float oralGrade) {
        this.oralGrade = oralGrade;
    }

    public Float getMidGrade() {
        return midGrade;
    }

    public void setMidGrade(Float midGrade) {
        this.midGrade = midGrade;
    }  
    
}
