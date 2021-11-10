package com.sis.dto;

public class DepartmentDTO extends BaseDTO{

    private String code;
    private String nameAr;
    private String nameEn;
    private Long college_id ;
    private String college_name_ar ;
    private String getCollege_name_en ;

    public DepartmentDTO() {
    }

    public DepartmentDTO(Long id ,String code, String nameAr, String nameEn,
                         Long college_id, String college_name_ar, String getCollege_name_en) {
        super(id);
        this.code = code;
        this.nameAr = nameAr;
        this.nameEn = nameEn;
        this.college_id = college_id;
        this.college_name_ar = college_name_ar;
        this.getCollege_name_en = getCollege_name_en;
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

    public Long getCollege_id() {
        return college_id;
    }

    public void setCollege_id(Long college_id) {
        this.college_id = college_id;
    }

    public String getCollege_name_ar() {
        return college_name_ar;
    }

    public void setCollege_name_ar(String college_name_ar) {
        this.college_name_ar = college_name_ar;
    }

    public String getGetCollege_name_en() {
        return getCollege_name_en;
    }

    public void setGetCollege_name_en(String getCollege_name_en) {
        this.getCollege_name_en = getCollege_name_en;
    }
}
