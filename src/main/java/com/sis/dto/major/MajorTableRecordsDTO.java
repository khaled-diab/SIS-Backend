package com.sis.dto.major;

import com.sis.dto.BaseDTO;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@Validated
public class MajorTableRecordsDTO extends BaseDTO {

    private String nameAr;

    private String nameEn;

    private String collegeName;

    private String departmentName;
}
