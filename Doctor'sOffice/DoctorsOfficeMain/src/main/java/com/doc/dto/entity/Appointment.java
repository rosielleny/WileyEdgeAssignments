package com.doc.dto.entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Appointment {
//	CREATE TABLE Appointment (
//			  appointment_id INT PRIMARY KEY,
//			  patient_id INT,
//			  doctor_id INT,
//			  appointment_time DATETIME,
//			  appointment_status VARCHAR(20),
//			  additional_notes TEXT,
//			  FOREIGN KEY (patient_id) REFERENCES Patient(patient_id),
//			  FOREIGN KEY (doctor_id) REFERENCES Doctor(doctor_id));
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int appointmentId;
    private int patientId;
    private int doctorId;
    private LocalDateTime appointmentTime;
    private String appointmentStatus;
    private String additionalNotes;
    
    
    public Appointment() {
    	
    }
    
	public Appointment(int appointmentId, int patientId, int doctorId, LocalDateTime appointmentTime,
			String appointmentStatus, String additionalNotes) {
		super();
		this.appointmentId = appointmentId;
		this.patientId = patientId;
		this.doctorId = doctorId;
		this.appointmentTime = appointmentTime;
		this.appointmentStatus = appointmentStatus;
		this.additionalNotes = additionalNotes;
	}

	public int getAppointmentId() {
		return appointmentId;
	}

	public void setAppointmentId(int appointmentId) {
		this.appointmentId = appointmentId;
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

	public LocalDateTime getAppointmentTime() {
		return appointmentTime;
	}

	public void setAppointmentTime(String appointmentTime) {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
	    this.appointmentTime = LocalDateTime.parse(appointmentTime + ":00", formatter);
	}

	public String getAppointmentStatus() {
		return appointmentStatus;
	}

	public void setAppointmentStatus(String appointmentStatus) {
		this.appointmentStatus = appointmentStatus;
	}

	public String getAdditionalNotes() {
		return additionalNotes;
	}

	public void setAdditionalNotes(String additionalNotes) {
		this.additionalNotes = additionalNotes;
	}
    
    
    

}
