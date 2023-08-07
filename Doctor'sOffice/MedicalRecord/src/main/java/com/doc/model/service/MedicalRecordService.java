package com.doc.model.service;

import java.util.List;

import com.doc.dto.entity.MedicalRecord;

public interface MedicalRecordService {

    MedicalRecord deleteMedicalRecord(int medicalRecordId);
    List<MedicalRecord> getAllMedicalRecords();
    MedicalRecord createMedicalRecord(MedicalRecord medicalRecord);
    MedicalRecord getMedicalRecordById(int medicalRecordId);
    boolean updateMedicalRecord(int medicalRecordId, MedicalRecord updatedMedicalRecord);

}
