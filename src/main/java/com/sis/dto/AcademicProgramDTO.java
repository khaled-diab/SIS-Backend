package com.sis.dto;

public class AcademicProgramDTO extends BaseDTO{

    private String code;
    private String nameAr;
    private String nameEn;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNameAr() {
        return nameAr;
    }

    public void setNameAr(String nameArb) {
        this.nameAr = nameArb;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }
}
