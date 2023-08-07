package com.patient.model.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.patient.dto.entity.Patient;
import com.patient.model.dao.PatientDao;

@Service
public class PatientServiceImpl implements PatientService {

    @Autowired
    PatientDao patientDao;

    @Override
    public Patient getPatientByPatientId(int patientId) {
        return patientDao.findById(patientId).orElse(null);
    }

    @Override
    public Patient getPatientByPatientName(String patientName) {
        return patientDao.getPatientByPatientName(patientName);
    }

    @Override
    public List<Patient> getAllPatients() {
        return patientDao.findAll();
    }

    @Override
    @Transactional
    public Patient updatePatientDetailsByPatientId(
            int patientId,
            String patientName,
            String contactInfo,
            LocalDate dateOfBirth,
            String medicalHistory) {

        Optional<Patient> optionalPatient = patientDao.findById(patientId);

        if (optionalPatient.isPresent()) {
            Patient patientToUpdate = optionalPatient.get();
            patientToUpdate.setPatientName(patientName);
            patientToUpdate.setContactInfo(contactInfo);
            patientToUpdate.setDateOfBirth(dateOfBirth);
            patientToUpdate.setMedicalHistory(medicalHistory);
            return patientDao.save(patientToUpdate);
        } else {
            return null;
        }
    }

    @Override
    @Transactional
    public Patient deletePatientByPatientId(int patientId) {
        Optional<Patient> optionalPatient = patientDao.findById(patientId);
        if (optionalPatient.isPresent()) {
            Patient patientToDelete = optionalPatient.get();
            patientDao.deleteById(patientId);
            return patientToDelete;
        } else {
            return null;
        }
    }

    @Override
    public boolean addPatient(Patient patient) {
        try {
            patientDao.save(patient);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

}
