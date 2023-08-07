package com.doc.dto.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Patient {
	
//	CREATE TABLE Patient (
//			  patient_id INT PRIMARY KEY,
//			  patient_name VARCHAR(50),
//			  contact_info VARCHAR(100),
//			  date_of_birth DATE,
//			  medical_history TEXT);

	@Id
	private int patientId;
    private String patientName;
    private String contactInfo;
    private LocalDate dateOfBirth;
    private String medicalHistory;

	public Patient(int patientId, String patientName, String contactInfo, LocalDate dateOfBirth,
			String medicalHistory) {
		super();
		this.patientId = patientId;
		this.patientName = patientName;
		this.contactInfo = contactInfo;
		this.dateOfBirth = dateOfBirth;
		this.medicalHistory = medicalHistory;
	}
	
	public Patient() {
		
	}
	
	public int getPatientId() {
		return patientId;
	}
	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}
	public String getPatientName() {
		return patientName;
	}
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}
	public String getContactInfo() {
		return contactInfo;
	}
	public void setContactInfo(String contactInfo) {
		this.contactInfo = contactInfo;
	}
	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getMedicalHistory() {
		return medicalHistory;
	}
	public void setMedicalHistory(String medicalHistory) {
		this.medicalHistory = medicalHistory;
	}
    
    

}
