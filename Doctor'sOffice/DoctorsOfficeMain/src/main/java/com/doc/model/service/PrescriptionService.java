package com.doc.model.service;

import com.doc.dto.entity.Prescription;

import java.util.List;
import java.util.Optional;

public interface PrescriptionService {
    List<Prescription> getAllPrescriptions();
    List<Prescription> getPrescriptionsByPatientId(int patientId);
    List<Prescription> getPrescriptionsByDoctorId(int doctorId);
    Prescription getPrescriptionById(int prescriptionId);
    Prescription createPrescription(Prescription prescription);
    Prescription updatePrescription(Prescription prescription);
    Optional<Prescription> deletePrescriptionById(int prescriptionId);
}
