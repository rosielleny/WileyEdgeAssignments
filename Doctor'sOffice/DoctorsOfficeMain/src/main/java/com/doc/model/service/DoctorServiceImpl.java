package com.doc.model.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.doc.dto.entity.Doctor;
import com.doc.dto.entity.DoctorList;

@Service
public class DoctorServiceImpl implements DoctorService {

	@Autowired
	private RestTemplate restTemplate;
	
	@Override
	public List<Doctor> getDoctorById(int docId) {
	    List<Doctor> doctors = new ArrayList<Doctor>();
	    
	    Doctor doctor = restTemplate.getForObject("http://localhost:8090/displayDoctorById/"+docId, Doctor.class);
	    
	    if (doctor != null) {
	        doctors.add(doctor);
	    }

	    return doctors;
	}

	@Override
	public List<Doctor> getDoctorByName(String docName) {
		List<Doctor> doctors = new ArrayList<Doctor>();
	    
	    Doctor doctor = restTemplate.getForObject("http://localhost:8090/displayDoctorByName/"+docName, Doctor.class);
	    
	    if (doctor != null) {
	        doctors.add(doctor);
	    }

	    return doctors;
	}

	@Override
	public List<Doctor> getAllDoctors() {
	    DoctorList doctorList = restTemplate.getForObject("http://localhost:8090/displayAllDoctors", DoctorList.class);

	    if (doctorList != null) {
	        return doctorList.getDoctor();
	    } else {
	        return new ArrayList<>();
	    }
	}

	@Override
	public Doctor updateDoctor(Doctor doctor) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<Doctor> requestEntity = new HttpEntity<>(doctor, headers);

		ResponseEntity<Doctor> responseEntity = restTemplate.exchange(
				"http://localhost:8090/updateDoctorEntry",
				HttpMethod.POST,
				requestEntity,
				Doctor.class
		);

		return responseEntity.getBody();
	}
	
	public Doctor deleteDoctor(int docId) {
        ResponseEntity<Doctor> response = restTemplate.exchange("http://localhost:8090/deleteDoctorEntry/" + docId,HttpMethod.DELETE, null, Doctor.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            return response.getBody();
        } else {
            return null;
        }
    }

	public boolean addDoctor(Doctor doctor) {
	    Doctor createdDoctor = restTemplate.postForObject("http://localhost:8090/createDoctorEntry", doctor, Doctor.class);

	    return createdDoctor != null;
	}

}
