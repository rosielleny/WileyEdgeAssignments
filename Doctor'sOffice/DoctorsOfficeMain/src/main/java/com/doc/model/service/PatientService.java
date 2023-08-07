package com.doc.model.service;

import java.time.LocalDate;
import java.util.List;

import com.doc.dto.entity.Patient;

public interface PatientService {
	
	public List<Patient> getPatientById(int patientId);
	public List<Patient> getPatientsByName(String patientName);
	public List<Patient> getAllPatients();
	public Patient updatePatientDetailsByPatientId(int patientId, String patientName, String contactInfo, LocalDate dateOfBirth, String medicalHistory);
	public Patient deletePatient(int patientId);
	public boolean addPatient(Patient patient);
}
