package com.patient.model.service;

import java.time.LocalDate;
import java.util.List;
import com.patient.dto.entity.Patient;

public interface PatientService {
	
	Patient getPatientByPatientId(int patientId);
	Patient getPatientByPatientName(String patientName);
	List<Patient> getAllPatients();
	Patient updatePatientDetailsByPatientId(int patientId, String patientName, String contactInfo, LocalDate dateOfBirth, String medicalHistory);
	Patient deletePatientByPatientId(int patientId);
	boolean addPatient(Patient patient);
}
