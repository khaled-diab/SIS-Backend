package com.sis.dto;


public class CourseDTO extends BaseDTO {

    private String code;
    private String nameAr;
    private String nameEn;
    private Float theoreticalHours;
    private Float exercisesHours;
    private Float practicalHours;
    private Float totalHours;
    private Integer weeks;
    private Float finalGrade;
    private Float finalExamGrade;
    private Float practicalGrade;
    private Float oralGrade;
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
