package com.doc.dto.entity.composite;

import java.time.LocalDateTime;

import javax.persistence.Id;

public class AppointmentPatientDoctor {
	
	@Id
	private int appointmentId;
    private String patientName;
    private String doctorName;
    private LocalDateTime appointmentTime;
    private String appointmentStatus;
    private String additionalNotes;
    
	public AppointmentPatientDoctor(int appointmentId, String patientName, String doctorName,
			LocalDateTime appointmentTime, String appointmentStatus, String additionalNotes) {
		super();
		this.appointmentId = appointmentId;
		this.patientName = patientName;
		this.doctorName = doctorName;
		this.appointmentTime = appointmentTime;
		this.appointmentStatus = appointmentStatus;
		this.additionalNotes = additionalNotes;
	}

    public AppointmentPatientDoctor() {
    	
    }

	public int getAppointmentId() {
		return appointmentId;
	}

	public void setAppointmentId(int appointmentId) {
		this.appointmentId = appointmentId;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	public LocalDateTime getAppointmentTime() {
		return appointmentTime;
	}

	public void setAppointmentTime(LocalDateTime appointmentTime) {
		this.appointmentTime = appointmentTime;
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
