package com.sis.dto;


import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class AcademicYearDTO extends BaseDTO {
    private String code, name;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date start_date;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date end_date;

    public AcademicYearDTO() {

    }

    public AcademicYearDTO(String code, String name, Date start_date, Date end_date) {
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

}
