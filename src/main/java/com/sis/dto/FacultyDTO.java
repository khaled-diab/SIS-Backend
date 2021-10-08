package com.sis.dto;

import java.util.Date;

public class FacultyDTO extends BaseDTO {

	private String nameAr;
	private String nameEn;
	private String nationality;
	private String nationalID;
	private Date birthDate;	
	private String alternativeMail;
	private String phone;
	private String degree;
	
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
	public String getNationalID() {
		return nationalID;
	}
	public void setNationalID(String nationalID) {
		this.nationalID = nationalID;
	}
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
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
	public String getDegree() {
		return degree;
	}
	public void setDegree(String degree) {
		this.degree = degree;
	}
	
	
}
