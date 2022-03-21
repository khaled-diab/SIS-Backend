package com.sis.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class AcademicTermDTO extends BaseDTO {
    private String code, name;
    private Date start_date, end_date;
    private AcademicYearDTO academicYearDTO ;

}
