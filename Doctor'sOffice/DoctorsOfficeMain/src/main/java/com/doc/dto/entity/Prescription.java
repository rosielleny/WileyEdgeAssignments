package com.doc.dto.entity;

import javax.persistence.*;

@Entity
public class Prescription {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "prescription_id")
	private int prescriptionId;

	@Column(name = "patient_id")
	private int patientId;

	@Column(name = "doctor_id")
	private int doctorId;

	@Column(name = "medication_name")
	private String medicationName;

	@Column(name = "dosage_instructions")
	private String dosageInstructions;

	@Column(name = "duration")
	private String duration;

	public Prescription() {
	}

	public Prescription(int prescriptionId, int patientId, int doctorId, String medicationName,
			String dosageInstructions, String duration) {
		super();
		this.prescriptionId = prescriptionId;
		this.patientId = patientId;
		this.doctorId = doctorId;
		this.medicationName = medicationName;
		this.dosageInstructions = dosageInstructions;
		this.duration = duration;
	}

	public int getPrescriptionId() {
		return prescriptionId;
	}

	public void setPrescriptionId(int prescriptionId) {
		this.prescriptionId = prescriptionId;
	}

	public int getPatientId() {
		return patientId;
	}

	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}

	public int getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(int doctorId) {
		this.doctorId = doctorId;
	}

	public String getMedicationName() {
		return medicationName;
	}

	public void setMedicationName(String medicationName) {
		this.medicationName = medicationName;
	}

	public String getDosageInstructions() {
		return dosageInstructions;
	}

	public void setDosageInstructions(String dosageInstructions) {
		this.dosageInstructions = dosageInstructions;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

}
