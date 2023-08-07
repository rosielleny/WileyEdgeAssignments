package com.doc.model.dao;

import com.doc.dto.entity.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrescriptionDao extends JpaRepository<Prescription, Integer> {
    List<Prescription> getPrescriptionByPatientId(int patientId);
    List<Prescription> getPrescriptionByDoctorId(int doctorId);
}
