package com.sis.dto;

import java.util.Date;

public class AcademicTermDTO extends BaseDTO {
    private String code, name;
    private Date start_date, end_date;
    private Long id_academic_year ;
    private String name_academic_year ;
    public AcademicTermDTO() {
    }

    public AcademicTermDTO(String code, String name, Date start_date, Date end_date, Long id_academic_year
        , String name_academic_year) {
        this.code = code;
        this.name = name;
        this.start_date = start_date;
        this.end_date = end_date;
        this.id_academic_year = id_academic_year ;
        this.name_academic_year = name_academic_year ;
    }

    public Long getId_academic_year() {
        return id_academic_year;
    }

    public void setId_academic_year(Long id_academic_year) {
        this.id_academic_year = id_academic_year;
    }

    public String getName_academic_year() {
        return name_academic_year;
    }

    public void setName_academic_year(String name_academic_year) {
        this.name_academic_year = name_academic_year;
    }

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

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }
}
