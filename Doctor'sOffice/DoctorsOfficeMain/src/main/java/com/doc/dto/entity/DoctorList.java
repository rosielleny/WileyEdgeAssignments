package com.doc.dto.entity;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DoctorList {

	List <Doctor> doctor;

	public List<Doctor> getDoctor() {
		return doctor;
	}

	public void setDoctor(List<Doctor> doctor) {
		this.doctor = doctor;
	}

	public DoctorList(List<Doctor> doctor) {
		super();
		this.doctor = doctor;
	}
	
	public DoctorList() {
		
	}
}
