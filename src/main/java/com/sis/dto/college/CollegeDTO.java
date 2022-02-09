package com.sis.dto.college;

import com.sis.dto.BaseDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@Builder
public class CollegeDTO extends BaseDTO {

    private String code;
    private String nameAr;
    private String nameEn;


}
