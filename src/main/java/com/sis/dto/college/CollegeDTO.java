package com.sis.dto.college;

import com.sis.dto.BaseDTO;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CollegeDTO extends BaseDTO {


    private String code;

    private String nameAr;

    private String nameEn;

}
