package com.doc.model.service;

import java.time.LocalDate;
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

import com.doc.dto.entity.Patient;
import com.doc.dto.entity.PatientList;

@Service
public class PatientServiceImpl implements PatientService {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public List<Patient> getPatientById(int patientId) {
        List<Patient> patients = new ArrayList<Patient>();

        Patient patient = restTemplate.getForObject("http://localhost:4040/displayPatientById/" + patientId, Patient.class);

        if (patient != null) {
            patients.add(patient);
        }

        return patients;
    }

    @Override
    public List<Patient> getPatientsByName(String patientName) {
        List<Patient> patients = new ArrayList<Patient>();

        Patient patient = restTemplate.getForObject("http://localhost:4040/displayPatientByName/" + patientName, Patient.class);

        if (patient != null) {
            patients.add(patient);
        }

        return patients;
    }

    @Override
    public List<Patient> getAllPatients() {
        PatientList patientList = restTemplate.getForObject("http://localhost:4040/displayAllPatients", PatientList.class);

        if (patientList != null) {
            return patientList.getPatient();
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    public Patient updatePatientDetailsByPatientId(int patientId, String patientName, String contactInfo, LocalDate dateOfBirth, String medicalHistory) {
        Patient patient = new Patient(patientId, patientName, contactInfo, dateOfBirth, medicalHistory);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Patient> requestEntity = new HttpEntity<>(patient, headers);

        ResponseEntity<Patient> responseEntity = restTemplate.exchange("http://localhost:4040/updatePatientEntry", HttpMethod.PUT, requestEntity, Patient.class);

        return responseEntity.getBody();
    }

    @Override
    public Patient deletePatient(int patientId) {
        ResponseEntity<Patient> response = restTemplate.exchange("http://localhost:4040/deletePatientEntry/" + patientId, HttpMethod.DELETE, null, Patient.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            return response.getBody();
        } else {
            return null;
        }
    }

    @Override
    public boolean addPatient(Patient patient) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Patient> requestEntity = new HttpEntity<>(patient, headers);

        ResponseEntity<Patient> responseEntity = restTemplate.exchange("http://localhost:4040/createPatientEntry", HttpMethod.POST, requestEntity, Patient.class);

        HttpStatus statusCode = responseEntity.getStatusCode();
        return statusCode == HttpStatus.CREATED;
    }
}
