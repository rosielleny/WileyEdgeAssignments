package com.doc.model.service;

import java.util.List;
import java.util.Optional;

import com.doc.dto.entity.MedicalRecord;

public interface MedicalRecordService {

	List<MedicalRecord> getAllMedicalRecords();
    MedicalRecord getMedicalRecordById(int medicalRecordId);
    MedicalRecord createMedicalRecord(MedicalRecord medicalRecord);
    MedicalRecord updateMedicalRecord(MedicalRecord medicalRecord);
    Optional<MedicalRecord> deleteMedicalRecordById(int medicalRecordId);
	
}
