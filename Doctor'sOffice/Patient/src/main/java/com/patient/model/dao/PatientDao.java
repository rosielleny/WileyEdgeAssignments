package com.patient.model.dao;

import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.patient.dto.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Transactional
@Repository
public interface PatientDao extends JpaRepository<Patient, Integer> {

	@Query ("FROM Patient WHERE patientId = :patientId")
	Patient getPatientByPatientId(@Param ("patientId") int patientId);

	@Query ("FROM Patient WHERE patientId = :patientName")
    Patient getPatientByPatientName(@Param ("patientName")String patientName);

	@Query("FROM Patient")
    List<Patient> getAllPatients();

    @Modifying
    @Query(value = "UPDATE Patient SET patientName = :patientName, contactInfo = :contactInfo, dateOfBirth = :dateOfBirth, medicalHistory = :medicalHistory WHERE patientId = :patientId")
    void updatePatientDetailsByPatientId(
            @Param("patientName") String patientName,
            @Param("contactInfo") String contactInfo,
            @Param("dateOfBirth") LocalDate dateOfBirth,
            @Param("medicalHistory") String medicalHistory,
            @Param("patientId") Integer patientId);

    @Modifying
    @Query(value = "INSERT INTO Patient (patientName, contactInfo, dateOfBirth, medicalHistory) VALUES (:patientName, :contactInfo, :dateOfBirth, :medicalHistory)", nativeQuery = true)
    void addPatient(
            @Param("patientName") String patientName,
            @Param("contactInfo") String contactInfo,
            @Param("dateOfBirth") LocalDate dateOfBirth,
            @Param("medicalHistory") String medicalHistory);
    
    @Modifying
    @Query ("DELETE FROM Patient WHERE patientId = :patientId")
    Patient deletePatientByPatientId(@Param ("patientId") int patientId);
}
