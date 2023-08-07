package com.doc.dto.entity;

import java.util.List;

public class PatientList {

	List <Patient> patient;

	public PatientList(List<Patient> patient) {
		super();
		this.patient = patient;
	}

	public PatientList() {
	}

	public List<Patient> getPatient() {
		return patient;
	}

	public void setPatient(List<Patient> patient) {
		this.patient = patient;
	}
	
	
}
