package com.doc.model.service;

import java.util.List;
import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.doc.model.dao.MedicalRecordDao;
import com.doc.dto.entity.MedicalRecord;

@Service
public class MedicalRecordServiceImpl implements MedicalRecordService {
	
	@Autowired
    MedicalRecordDao medicalRecordDao;

    @Autowired
    public MedicalRecordServiceImpl(MedicalRecordDao medicalRecordDao) {
        this.medicalRecordDao = medicalRecordDao;
    }
    
    @Override
    public MedicalRecord createMedicalRecord(MedicalRecord medicalRecord) {
        int medicalRecordId = medicalRecord.getMedicalRecordId();
        if (medicalRecordId != 0 && medicalRecordDao.existsById(medicalRecordId)) {
            throw new IllegalArgumentException("A MedicalRecord with ID " + medicalRecordId + " already exists.");
        }
        return medicalRecordDao.save(medicalRecord);
    }

    @Override
    public MedicalRecord getMedicalRecordById(int medicalRecordId) {
        return medicalRecordDao.findById(medicalRecordId).orElse(null);
    }

//    @Override
//    public boolean updateMedicalRecord(int medicalRecordId, MedicalRecord updatedMedicalRecord) {
//        if (medicalRecordId == 0) {
//            throw new IllegalArgumentException("Medical record ID cannot be null");
//        }
//        if (!medicalRecordDao.existsById(medicalRecordId)) {
//            throw new IllegalArgumentException("Medical record does not exist");
//        }
//        updatedMedicalRecord.setMedicalRecordId(medicalRecordId);
//        medicalRecordDao.save(updatedMedicalRecord);
//        return true;
//    }

    @Override
    public boolean updateMedicalRecord(int medicalRecordId, MedicalRecord updatedMedicalRecord) {
        MedicalRecord existingMedicalRecord = medicalRecordDao.findById(medicalRecordId).orElse(null);
        if (existingMedicalRecord != null) {
            existingMedicalRecord.setDiagnoses(updatedMedicalRecord.getDiagnoses());
            existingMedicalRecord.setTreatments(updatedMedicalRecord.getTreatments());
            existingMedicalRecord.setMedications(updatedMedicalRecord.getMedications());
            medicalRecordDao.save(existingMedicalRecord);
            return true; // Return true to indicate the successful update
        } else {
            return false; // Return false to indicate that the record with the given ID was not found
        }
    }
    
    @Override
    public MedicalRecord deleteMedicalRecord(int medicalRecordId) {
        Optional<MedicalRecord> optionalMedicalRecord = medicalRecordDao.findById(medicalRecordId);
        if (optionalMedicalRecord.isPresent()) {
            MedicalRecord medicalRecord = optionalMedicalRecord.get();
            medicalRecordDao.deleteById(medicalRecordId);
            return medicalRecord;
        }
        return null;
    }

    @Override
    public List<MedicalRecord> getAllMedicalRecords() {
        return medicalRecordDao.findAll();
    }
}
