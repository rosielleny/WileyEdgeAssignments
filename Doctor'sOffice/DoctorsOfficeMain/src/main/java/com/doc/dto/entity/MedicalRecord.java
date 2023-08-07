package com.doc.dto.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
@Table(name = "MedicalRecord")
public class MedicalRecord {

	// CREATE TABLE MedicalRecord (
	// medical_record_id INT PRIMARY KEY,
	// patient_id INT,
	// diagnoses VARCHAR(200),
	// treatments VARCHAR(200),
	// medications VARCHAR(200),
	// FOREIGN KEY (patient_id) REFERENCES Patient(patient_id));

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "medical_record_id")
	private int medicalRecordId;
//	@NotNull(message = "ID cannot be left blank")
	@Column(name = "patient_id", nullable = false)
	private int patientId;
	private String diagnoses;
	private String treatments;
	private String medications;

	public MedicalRecord(int medicalRecordId, int patientId, String diagnoses, String treatments, String medications) {
		super();
		this.medicalRecordId = medicalRecordId;
		this.patientId = patientId;
		this.diagnoses = diagnoses;
		this.treatments = treatments;
		this.medications = medications;
	}

	public MedicalRecord() {

	}

	public int getMedicalRecordId() {
		return medicalRecordId;
	}

	public void setMedicalRecordId(int medicalRecordId) {
		this.medicalRecordId = medicalRecordId;
	}

	public int getPatientId() {
		return patientId;
	}

	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}

	public String getDiagnoses() {
		return diagnoses;
	}

	public void setDiagnoses(String diagnoses) {
		this.diagnoses = diagnoses;
	}

	public String getTreatments() {
		return treatments;
	}

	public void setTreatments(String treatments) {
		this.treatments = treatments;
	}

	public String getMedications() {
		return medications;
	}

	public void setMedications(String medications) {
		this.medications = medications;
	}

}
