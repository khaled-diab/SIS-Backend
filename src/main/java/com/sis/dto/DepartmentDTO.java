package com.sis.dto;

import com.sis.dto.college.CollegeDTO;

public class DepartmentDTO extends BaseDTO{

    private String code;
    private String nameAr;
    private String nameEn;
//    private Long college_id ;
//    private String college_name_ar ;
//    private String getCollege_name_en ;
    private CollegeDTO collegeDTO;
    public DepartmentDTO() {
    }

    public DepartmentDTO(Long id ,String code, String nameAr, String nameEn ) {
        super(id);
        this.code = code;
        this.nameAr = nameAr;
        this.nameEn = nameEn;
    }

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

    public CollegeDTO getCollegeDTO() {
        return collegeDTO;
    }

    public void setCollegeDTO(CollegeDTO collegeDTO) {
        this.collegeDTO = collegeDTO;
    }
}
