package com.doc.doctor.service;

import java.util.List;

import com.doc.doctor.dto.entity.Doctor;

public interface DoctorService {
	
	Doctor getDocByDocId (int docId);
	Doctor getDocByDocName (String DocName);
	List<Doctor> getAllDoctors();
	/*
	Doctor updateDocDetailsByDocId (int docId, String docName, String docQualification, String docSpecialty, String docGender, String docDays, String docHours);

	 */
	Doctor updateDocDetailsByDocId(Doctor doctor);
	Doctor deleteDocByDocId (int docId);
	/*
	boolean deleteDocByDocId(int docId);

	 */
	boolean addDoctor(Doctor doctor);
	
}
