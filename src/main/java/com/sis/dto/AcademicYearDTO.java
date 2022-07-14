package com.sis.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
public class AcademicYearDTO extends BaseDTO {
    private String code, name;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date start_date;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date end_date;
    private boolean status ;
    public AcademicYearDTO() {

    }

    public AcademicYearDTO(String code, String name, Date start_date, Date end_date) {
        this.code = code;
        this.name = name;
        this.start_date = start_date;
        this.end_date = end_date;
    }


}
