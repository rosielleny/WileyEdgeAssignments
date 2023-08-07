package com.doc.dto.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Doctor {
	
	@Id
	private Integer docId;
	private String docName;
	private String docQualification;
	private String docSpecialty;
	private String docGender;
	private String docDays;
	private String docHours;
	public Integer getDocId() {
		return docId;
	}
	public void setDocId(Integer docId) {
		this.docId = docId;
	}
	public String getDocName() {
		return docName;
	}
	public void setDocName(String docName) {
		this.docName = docName;
	}
	public String getDocQualification() {
		return docQualification;
	}
	public void setDocQualification(String docQualification) {
		this.docQualification = docQualification;
	}
	public String getDocSpecialty() {
		return docSpecialty;
	}
	public void setDocSpecialty(String docSpecialty) {
		this.docSpecialty = docSpecialty;
	}
	public String getDocGender() {
		return docGender;
	}
	public void setDocGender(String docGender) {
		this.docGender = docGender;
	}
	public String getDocDays() {
		return docDays;
	}
	public void setDocDays(String docDays) {
		this.docDays = docDays;
	}
	public String getDocHours() {
		return docHours;
	}
	public void setDocHours(String docHours) {
		this.docHours = docHours;
	}
	public Doctor(Integer docId, String docName, String docQualification, String docSpecialty, String docGender,
			String docDays, String docHours) {
		super();
		this.docId = docId;
		this.docName = docName;
		this.docQualification = docQualification;
		this.docSpecialty = docSpecialty;
		this.docGender = docGender;
		this.docDays = docDays;
		this.docHours = docHours;
	}
	
	public Doctor() {
		
	}

}
