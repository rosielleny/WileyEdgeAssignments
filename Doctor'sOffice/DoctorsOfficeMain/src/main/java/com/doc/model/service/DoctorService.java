package com.doc.model.service;

import java.util.List;

import com.doc.dto.entity.Doctor;

public interface DoctorService {
	
	public List<Doctor> getDoctorById(int docId);
	public List<Doctor> getDoctorByName(String docName);
	public List<Doctor> getAllDoctors();
	public Doctor updateDoctor(Doctor doctor);
	public Doctor deleteDoctor(int docId);
	public boolean addDoctor(Doctor doctor);
	
}
