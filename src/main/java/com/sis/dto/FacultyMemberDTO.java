package com.sis.dto;

import com.sis.entities.College;
import com.sis.entities.Department;

import java.util.Date;

public class FacultyMemberDTO extends BaseDTO {

	private String nameAr;
	private String nameEn;
	private String degree;
	private String universityMail;
	private String alternativeMail;
	private String nationalID;
	private String nationality;
	private String phone;
	private Date birthDate;
//	private College collegeId;
//	private Department deptId;

//	public College getCollegeId() {
//		return collegeId;
//	}
//
//	public void setCollegeId(College collegeId) {
//		this.collegeId = collegeId;
//	}
//
//	public Department getDeptId() {
//		return deptId;
//	}
//
//	public void setDeptId(Department deptId) {
//		this.deptId = deptId;
//	}

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

	public String getDegree() {
		return degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}
}
