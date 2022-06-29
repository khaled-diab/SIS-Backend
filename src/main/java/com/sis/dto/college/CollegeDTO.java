package com.sis.dto.college;

import com.sis.dto.BaseDTO;
import lombok.*;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CollegeDTO extends BaseDTO {

    private String code;
    private String nameAr;
    private String nameEn;


}
