package com.patient.resource;

import com.patient.dto.entity.Patient;
import com.patient.dto.entity.PatientList;
import com.patient.model.service.PatientService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PatientResource {

    @Autowired
    private PatientService patientService;

    @GetMapping(path = "/displayPatientById/{patientId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Patient> displayPatientById(@PathVariable("patientId") int patientId) {
        Patient patient = patientService.getPatientByPatientId(patientId);
        return patient != null ? new ResponseEntity<>(patient, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(path = "/displayAllPatients", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PatientList> displayAllPatients() {
        List<Patient> patients = patientService.getAllPatients();
        PatientList patientList = new PatientList(patients);
        return !patients.isEmpty() ? new ResponseEntity<>(patientList, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(path = "/updatePatientEntry", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Patient> updatePatientEntry(@RequestBody Patient patient) {
        Patient updatedPatient = patientService.updatePatientDetailsByPatientId(
                patient.getPatientId(),
                patient.getPatientName(),
                patient.getContactInfo(),
                patient.getDateOfBirth(),
                patient.getMedicalHistory()
        );
        return updatedPatient != null ? new ResponseEntity<>(updatedPatient, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping(path = "/deletePatientEntry/{patientId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Patient> deletePatientEntry(@PathVariable int patientId) {
        Patient patient = patientService.deletePatientByPatientId(patientId);
        return patient != null ? new ResponseEntity<>(patient, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping(path = "/createPatientEntry", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Patient> createPatientEntry(@RequestBody Patient patient) {
        if (patientService.addPatient(patient))
            return new ResponseEntity<>(patient, HttpStatus.CREATED);
        else
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }
}
