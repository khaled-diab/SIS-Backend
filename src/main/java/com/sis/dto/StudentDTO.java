package com.sis.dto;

import java.util.Date;

public class StudentDTO extends BaseDTO {

    private long UniversityId;
    private String nameAr;
    private String nameEn;
    private String nationality;
    private String nationalId;
    private Date birthDate;
    private String universityMail;
    private String alternativeMail;
    private String phone;
    private String parentPhone;
    private  String level;
    private  String photo;
    /*Relations instances*/
   private  DepartmentDTO departmentDTO;
   private  CollegeDTO collegeDTO;
   private AcademicProgramDTO academicProgramDTO;




    public long getUniversityId() {
        return UniversityId;
    }

    public void setUniversityId(long universityId) {
        UniversityId = universityId;
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

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getNationalId() {
        return nationalId;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getUniversityMail() {
        return universityMail;
    }

    public void setUniversityMail(String universityMail) {
        this.universityMail = universityMail;
    }

    public String getAlternativeMail() {
        return alternativeMail;
    }

    public void setAlternativeMail(String alternativeMail) {
        this.alternativeMail = alternativeMail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getParentPhone() {
        return parentPhone;
    }

    public void setParentPhone(String parentPhone) {
        this.parentPhone = parentPhone;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public DepartmentDTO getDepartmentDTO() {
        return departmentDTO;
    }

    public void setDepartmentDTO(DepartmentDTO departmentDTO) {
        this.departmentDTO = departmentDTO;
    }

    public CollegeDTO getCollegeDTO() {
        return collegeDTO;
    }

    public void setCollageDTO(CollegeDTO collegeDTO) {
        this.collegeDTO = collegeDTO;
    }

    public AcademicProgramDTO getAcademicProgramDTO() {
        return academicProgramDTO;
    }

    public void setAcademicProgramDTO(AcademicProgramDTO academicProgramDTO) {
        this.academicProgramDTO = academicProgramDTO;
    }
}
