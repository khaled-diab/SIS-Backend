package com.sis.dto;

import java.util.Date;

public class AcademicTermDTO extends BaseDTO {
    private String code, name;
    private Date start_date, end_date;
//    private AcademicYearDTO academicYearDTO ;
    private String year_name ;
    private Long year_id ;
    public AcademicTermDTO() {
    }

    public AcademicTermDTO(String code, String name, Date start_date, Date end_date, Long id_academic_year
        , String name_academic_year) {
        this.code = code;
        this.name = name;
        this.start_date = start_date;
        this.end_date = end_date;
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

    public String getYear_name() {
        return year_name;
    }

    public void setYear_name(String year_name) {
        this.year_name = year_name;
    }

    public Long getYear_id() {
        return year_id;
    }

    public void setYear_id(Long year_id) {
        this.year_id = year_id;
    }
}
